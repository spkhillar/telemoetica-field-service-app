package com.telenoetica.service;

import com.telenoetica.jpa.entities.AndroidApplicationDownloadHistory;

public interface AndroidApplicationDownloadHistoryService extends BaseService<AndroidApplicationDownloadHistory> {

  public AndroidApplicationDownloadHistory createOrUpdateDownloadForUser(final String userDeviceId, final String userName);
}
