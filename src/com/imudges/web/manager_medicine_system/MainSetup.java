package com.imudges.web.manager_medicine_system;

/**
 * Created by yangyang on 2017/4/23.
 */

import com.imudges.web.manager_medicine_system.bean.Patient;
import com.imudges.web.manager_medicine_system.bean.User;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import java.util.Date;

public class MainSetup implements Setup {
    public static Ioc ioc;

    @Override
    public void destroy(NutConfig arg0) {
    }

    @Override
    public void init(NutConfig conf) {
        MainSetup.ioc = conf.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "com.imudges.web.manager_medicine_system", false);


        User user = null;
        if (dao.count(User.class) == 0) {
            user = new User();
            user.setUsername("test");
            user.setPassword("123456");
            user.setType("2");
            dao.insert(user);
        }

        Patient patient = null;
        if (dao.count(Patient.class) == 0) {
            patient = new Patient();
            patient.setAddTime(new Date(System.currentTimeMillis()));
            patient.setIdCard("152727199704062714");
            patient.setName("yy");
            patient.setNum("999999");
            patient.setPhoneNum("18647705052");
            patient.setSex("男");
            if (user != null) {
                patient.setUserId(user.getId() + "");
            }
            dao.insert(patient);
        }
    }
}