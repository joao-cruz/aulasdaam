package pt.iscte.weatherme;

import org.appcelerator.titanium.ITiAppInfo;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiProperties;
import org.appcelerator.kroll.common.Log;

/* GENERATED CODE
 * Warning - this class was generated from your application's tiapp.xml
 * Any changes you make here will be overwritten
 */
public final class WeathermeAppInfo implements ITiAppInfo
{
	private static final String LCAT = "AppInfo";

	public WeathermeAppInfo(TiApplication app) {
	}

	public String getDeployType() {
		return "development";
	}

	public String getId() {
		return "pt.iscte.weatherme";
	}

	public String getName() {
		return "weatherMe";
	}

	public String getVersion() {
		return "1.0";
	}

	public String getPublisher() {
		return "carlosserrao";
	}

	public String getUrl() {
		return "http://www.iscte.pt";
	}

	public String getCopyright() {
		return "2014 by carlosserrao";
	}

	public String getDescription() {
		return "not specified";
	}

	public String getIcon() {
		return "appicon.png";
	}

	public boolean isAnalyticsEnabled() {
		return true;
	}

	public String getGUID() {
		return "ad7f1c2f-4193-4825-9093-ba5495269c61";
	}

	public boolean isFullscreen() {
		return false;
	}

	public boolean isNavBarHidden() {
		return false;
	}
}
