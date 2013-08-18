import org.junit.Test;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.telenoetica.scheduler.job.DieselDetailsReportJob;
import com.telenoetica.service.test.BaseServiceTest;

@ContextConfiguration(locations = { "classpath:jobs-test-context.xml" }, inheritLocations = true)
public class DieselDetailsReportTest extends BaseServiceTest {

	@Autowired
	private DieselDetailsReportJob dieselDetailsReport;

	@Test
	public void test() throws JobExecutionException {
		dieselDetailsReport.execute(null);
	}

}
