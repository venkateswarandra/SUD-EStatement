package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootWrapper {

    @JsonProperty("DataSource")
    private DataSourceOld dataSource;

    public DataSourceOld getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceOld dataSource) {
        this.dataSource = dataSource;
    }
}
