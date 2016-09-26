package com.lxy.retrofit;

import java.util.List;

/**
 * Created by lxy on
 */

public class HomeBean {



    public boolean error;

    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public Object who;

    }
}
