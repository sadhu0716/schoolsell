package com.schoolsell.entity;

import java.io.Serializable;

public class Bigkind implements Serializable {
    private Integer bigkindid;

    private String bigkindname;

    private String bigkindpicture;

    private static final long serialVersionUID = 1L;

    public Integer getBigkindid() {
        return bigkindid;
    }

    public void setBigkindid(Integer bigkindid) {
        this.bigkindid = bigkindid;
    }

    public String getBigkindname() {
        return bigkindname;
    }

    public void setBigkindname(String bigkindname) {
        this.bigkindname = bigkindname == null ? null : bigkindname.trim();
    }

    public String getBigkindpicture() {
        return bigkindpicture;
    }

    public void setBigkindpicture(String bigkindpicture) {
        this.bigkindpicture = bigkindpicture == null ? null : bigkindpicture.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bigkindid=").append(bigkindid);
        sb.append(", bigkindname=").append(bigkindname);
        sb.append(", bigkindpicture=").append(bigkindpicture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}