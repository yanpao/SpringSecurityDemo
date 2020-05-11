package com.wismap.springsecuritydemo.service;

public interface ISplitService {

    String GetUserRegion()throws Exception;

    String GetUserResult()throws Exception;

    String GetUserTemp()throws Exception;

    String SplitByLines(String lines)throws Exception;

    String SaveAttribute(String featureString)throws Exception;

    String Merge(String polygoncollection)throws Exception;

    Boolean Update(String polygoncollection)throws Exception;

    String Exam() throws Exception;

    String Delete(String polygoncollection)throws Exception;

}
