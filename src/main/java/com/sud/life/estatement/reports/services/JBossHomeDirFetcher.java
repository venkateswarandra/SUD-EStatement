package com.sud.life.estatement.reports.services;

import com.sud.life.estatement.utils.BposConfigConstants;
import com.sud.life.estatement.utils.UtilConstants;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class JBossHomeDirFetcher {

    public static ReportPaths FetchPathService() {
        String jbossHome = System.getProperty(UtilConstants.JBOSS_HOME_DIR);

        if (jbossHome == null || jbossHome.isEmpty()) {
            //System.out.println("JBoss home directory not found. Setting default.");
            jbossHome = BposConfigConstants.JBOSS_HOME_DEFAULT_LOCATION.getValue();
        }

        String jrxmlPath = jbossHome + File.separator + UtilConstants.REPORTS + File.separator + UtilConstants.JRXML ;
        String imagePath = jbossHome + File.separator + UtilConstants.REPORTS + File.separator + UtilConstants.ASSETS;
        String jsonPath = jbossHome + File.separator + UtilConstants.REPORTS + File.separator + UtilConstants.JSON;
        String jasperPath = jbossHome + File.separator + UtilConstants.REPORTS + File.separator + UtilConstants.JASPER;
        return new ReportPaths(jrxmlPath, imagePath,jsonPath,jasperPath);
    }

    public static class ReportPaths {
        private final String jrxmlPath;
        private final String imagePath;
        private String jsonPath;
        private String jasperPath;

        public ReportPaths(String jrxmlPath, String imagePath,String jsonPath,String jasperPath) {
            this.jrxmlPath = jrxmlPath;
            this.imagePath = imagePath;
            this.jsonPath=jsonPath;
            this.jasperPath=jasperPath;
        }

        public String getJrxmlPath() {
            return jrxmlPath;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getJsonPath() {
            return jsonPath;
        }
        public String getJasperPath() {
            return jasperPath;
        }
    }
}
