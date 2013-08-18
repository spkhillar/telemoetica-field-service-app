package com.telenoetica.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.AndroidApplicationDownloadHistory;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.AndroidApplicationDownloadHistoryDAO;
import com.telenoetica.service.AndroidApplicationDownloadHistoryService;
import com.telenoetica.service.impl.AbstractBaseService;
import com.telenoetica.service.util.ApplicationServiceException;

@Service("androidApplicationDownloadHistoryService")
@Transactional
public class AndroidApplicationDownloadHistoryServiceImpl extends AbstractBaseService implements AndroidApplicationDownloadHistoryService {

  @Autowired
  private AndroidApplicationDownloadHistoryDAO applicationDownloadHistoryDAO;

  @Override
  public AndroidApplicationDownloadHistory retrieve(final Long id) {
    return applicationDownloadHistoryDAO.findOne(id);
  }

  @Override
  public AndroidApplicationDownloadHistory saveOrUpdate(final AndroidApplicationDownloadHistory baseEntity) {
    return applicationDownloadHistoryDAO.save(baseEntity);
  }

  @Override
  public void delete(final AndroidApplicationDownloadHistory baseEntity) {
    applicationDownloadHistoryDAO.save(baseEntity);
  }

  @Override
  public AndroidApplicationDownloadHistory createOrUpdateDownloadForUser(final String userDeviceId, final String userName){
    logger.debug("### createOrUpdateDownloadForUser starts ###"+userDeviceId+":::"+userName);
    System.err.println("### createOrUpdateDownloadForUser starts ###"+userDeviceId+":::"+userName);
    if(StringUtils.isBlank(userName)){
      throw new ApplicationServiceException("User Name is required.");
    }
    User user = getUser(userName);
    AndroidApplicationDownloadHistory returnedApplicationDownloadHistory=null;
    AndroidApplicationDownloadHistory applicationDownloadHistory = applicationDownloadHistoryDAO.findByUser(user);
    if(applicationDownloadHistory == null){
      logger.debug("### Download history does not exists###");
      applicationDownloadHistory = new AndroidApplicationDownloadHistory(userDeviceId, user);
      returnedApplicationDownloadHistory = saveOrUpdate(applicationDownloadHistory);
    }else{
      if(StringUtils.isNotBlank(userDeviceId) && StringUtils.isBlank(applicationDownloadHistory.getUserDeviceId())){
        logger.debug("### Download history exists and updating record...###"+userDeviceId);
        applicationDownloadHistory.setUserDeviceId(userDeviceId);
        returnedApplicationDownloadHistory = saveOrUpdate(applicationDownloadHistory);
      }else if(StringUtils.isNotBlank(userDeviceId) && !StringUtils.equals(userDeviceId,applicationDownloadHistory.getUserDeviceId())){
        logger.debug("### Device is not assigned to current user...###"+userDeviceId);
        throw new ApplicationServiceException("Device is not assigned to current user.");
      }else{
        logger.debug("### Download history exists but cannot update with a new Device Id...###"+userDeviceId);
        throw new ApplicationServiceException("Device is not assigned to current user.");
      }
    }
    return returnedApplicationDownloadHistory;
  }


}
