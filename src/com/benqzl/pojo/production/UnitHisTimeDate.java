package com.benqzl.pojo.production;

import java.util.Comparator;
import java.util.Date;

public class UnitHisTimeDate implements Comparator<UnitHisTimeDate>{
    private String id;

    private String unitid;

    private Float pvalue;

    private Date cdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public Float getPvalue() {
        return pvalue;
    }

    public void setPvalue(Float pvalue) {
        this.pvalue = pvalue;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

	@Override
	public int compare(UnitHisTimeDate o1, UnitHisTimeDate o2) {
		return o1.getCdate().compareTo(o2.getCdate());
	}
}