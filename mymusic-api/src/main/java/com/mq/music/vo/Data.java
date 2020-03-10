package com.mq.music.vo;



import com.mq.music.bean.Manager;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Manager> managerList=new ArrayList<Manager>();
    private List<Manager> datas=new ArrayList<Manager>();



    public List<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
    }

    public List<Manager> getDatas() {
        return datas;
    }

    public void setDatas(List<Manager> datas) {
        this.datas = datas;
    }


}
