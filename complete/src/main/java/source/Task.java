package source;

public class Task
{
    private final String TASKID;
    private final String TASKTYPE;
    private final String PARENTTASK;
    private final String USER;
    private final String ACCOUNT;
    private final String SECURITYSYSTEM;
    private final String ENDPOINT;
    private final String ENTITLEMENT_VALUEKEY;
    private final String REQUESTKEY;
    private final String OWNERKEY;
    private final String CREATIONDATE;
    private final String STATUS;
    private final String upadteuser;
    private final String STARTDATE;
    private final String ENDDATE;
    private final String PROVISIONINGCOMMENTS;
    private final String PROVISIONINGMETADATA;
    private final String COMMENTS;
    private final String REQUESTACCESSKEY;
    private final String ENTITLEMENTTYPE;

    public Task(String TASKID, String TASKTYPE, String PARENTTASK, String USER, String ACCOUNT, String SECURITYSYSTEM, String ENDPOINT, String ENTITLEMENT_VALUEKEY, String REQUESTKEY, String OWNERKEY, String CREATIONDATE, String STATUS, String upadteuser, String STARTDATE, String ENDDATE, String PROVISIONINGCOMMENTS, String PROVISIONINGMETADATA, String COMMENTS, String REQUESTACCESSKEY, String ENTITLEMENTTYPE)
    {
        this.TASKID = TASKID;
        this.TASKTYPE = TASKTYPE;
        this.PARENTTASK = PARENTTASK;
        this.USER = USER;
        this.ACCOUNT = ACCOUNT;
        this.SECURITYSYSTEM = SECURITYSYSTEM;
        this.ENDPOINT = ENDPOINT;
        this.ENTITLEMENT_VALUEKEY = ENTITLEMENT_VALUEKEY;
        this.REQUESTKEY = REQUESTKEY;
        this.OWNERKEY = OWNERKEY;
        this.CREATIONDATE = CREATIONDATE;
        this.STATUS = STATUS;
        this.upadteuser = upadteuser;
        this.STARTDATE = STARTDATE;
        this.ENDDATE = ENDDATE;
        this.PROVISIONINGCOMMENTS = PROVISIONINGCOMMENTS;
        this.PROVISIONINGMETADATA = PROVISIONINGMETADATA;
        this.COMMENTS = COMMENTS;
        this.REQUESTACCESSKEY = REQUESTACCESSKEY;
        this.ENTITLEMENTTYPE = ENTITLEMENTTYPE;
    }

    public Task()
    {
        this.TASKID = "";
        this.TASKTYPE = "";
        this.PARENTTASK = "";
        this.USER = "";
        this.ACCOUNT = "";
        this.SECURITYSYSTEM = "";
        this.ENDPOINT = "";
        this.ENTITLEMENT_VALUEKEY = "";
        this.REQUESTKEY = "";
        this.OWNERKEY = "";
        this.CREATIONDATE = "";
        this.STATUS = "";
        this.upadteuser = "";
        this.STARTDATE = "";
        this.ENDDATE = "";
        this.PROVISIONINGCOMMENTS = "";
        this.PROVISIONINGMETADATA = "";
        this.COMMENTS = "";
        this.REQUESTACCESSKEY = "";
        this.ENTITLEMENTTYPE = "";
    }


    public String getTASKID()
    {
        return TASKID;
    }

    public String getTASKTYPE()
    {
        return TASKTYPE;
    }

    public String getPARENTTASK()
    {
        return PARENTTASK;
    }

    public String getUSER()
    {
        return USER;
    }

    public String getACCOUNT()
    {
        return ACCOUNT;
    }

    public String getSECURITYSYSTEM()
    {
        return SECURITYSYSTEM;
    }

    public String getENDPOINT()
    {
        return ENDPOINT;
    }

    public String getENTITLEMENT_VALUEKEY()
    {
        return ENTITLEMENT_VALUEKEY;
    }

    public String getREQUESTKEY()
    {
        return REQUESTKEY;
    }

    public String getOWNERKEY()
    {
        return OWNERKEY;
    }

    public String getCREATIONDATE()
    {
        return CREATIONDATE;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public String getUpadteuser()
    {
        return upadteuser;
    }

    public String getSTARTDATE()
    {
        return STARTDATE;
    }

    public String getENDDATE()
    {
        return ENDDATE;
    }

    public String getPROVISIONINGCOMMENTS()
    {
        return PROVISIONINGCOMMENTS;
    }

    public String getPROVISIONINGMETADATA()
    {
        return PROVISIONINGMETADATA;
    }

    public String getCOMMENTS()
    {
        return COMMENTS;
    }

    public String getREQUESTACCESSKEY()
    {
        return REQUESTACCESSKEY;
    }

    public String getENTITLEMENTTYPE()
    {
        return ENTITLEMENTTYPE;
    }
}
