package com.jay.appdemo1.modal.find;

import java.util.List;

/**
 * Created by liuzhi on 2019/1/2.
 */

public class Find_focus_model {

    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private Object updateTime;
    private int refreshCount;
    private int lastStartId;
    private List<ItemListBeanX> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getLastStartId() {
        return lastStartId;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }

    public List<ItemListBeanX> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBeanX> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBeanX {

        private String type;
        private DataBeanX data;
        private Object tag;
        private int id;
        private int adIndex;
        private int textState;

        public int getText() {
            return textState;
        }

        public void setText(int text) {
            this.textState = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBeanX getData() {
            return data;
        }

        public void setData(DataBeanX data) {
            this.data = data;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdIndex() {
            return adIndex;
        }

        public void setAdIndex(int adIndex) {
            this.adIndex = adIndex;
        }

        public static class DataBeanX {

            private String dataType;
            private HeaderBean header;
            private int count;
            private Object adTrack;
            private Object footer;
            private List<ItemListBean> itemList;

            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public HeaderBean getHeader() {
                return header;
            }

            public void setHeader(HeaderBean header) {
                this.header = header;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public Object getFooter() {
                return footer;
            }

            public void setFooter(Object footer) {
                this.footer = footer;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {

                this.itemList = itemList;
            }

            public static class HeaderBean {
                /**
                 * id : 142
                 * icon : http://img.kaiyanapp.com/be0cdbff1cf1755c7aa78edee08db42b.jpeg
                 * iconType : round
                 * title : TEDx
                 * subTitle : null
                 * description : 干货演讲，分享人生。
                 * actionUrl : eyepetizer://pgc/detail/142/?title=TEDx&userType=PGC&tabIndex=1
                 * adTrack : null
                 * follow : {"itemType":"author","itemId":142,"followed":false}
                 * ifPgc : true
                 * expert : false
                 */

                private int id;
                private String icon;
                private String iconType;
                private String title;
                private Object subTitle;
                private String description;
                private String actionUrl;
                private Object adTrack;
                private FollowBean follow;
                private boolean ifPgc;
                private boolean expert;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getIconType() {
                    return iconType;
                }

                public void setIconType(String iconType) {
                    this.iconType = iconType;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getSubTitle() {
                    return subTitle;
                }

                public void setSubTitle(Object subTitle) {
                    this.subTitle = subTitle;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public boolean isExpert() {
                    return expert;
                }

                public void setExpert(boolean expert) {
                    this.expert = expert;
                }

                public static class FollowBean {
                    /**
                     * itemType : author
                     * itemId : 142
                     * followed : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }
                }
            }

            public static class ItemListBean {
                /**
                 * type : video
                 * data : {"dataType":"VideoBeanForClient","id":142857,"title":"TED 演讲：来自另一星系的太空旅客","description":"2017 年 10 月，天体生物学家 Karen J. Meech 接到了 NASA 的电话，他们发现了一位来自另一星系的太空旅客 - 一个半英里长的星际彗星。这颗彗星最终被命名为「Oumuamua」，Meech 讲述了她的团队如何与时间赛跑，从地球探索这位太空旅客的故事。","library":"DEFAULT","tags":[{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=ç§\u0091æ\u0099®","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"IMPORTANT","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":546,"name":"宇宙","actionUrl":"eyepetizer://tag/546/?title=å®\u0087å®\u0099","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/7052c0f6e4267111b023d2541b1a7f07.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/7052c0f6e4267111b023d2541b1a7f07.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":38,"name":"演讲","actionUrl":"eyepetizer://tag/38/?title=æ¼\u0094è®²","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/bff90b4916df0e979f2dcdbd0fbd1467.png?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/bff90b4916df0e979f2dcdbd0fbd1467.png?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":400,"name":"TED","actionUrl":"eyepetizer://tag/400/?title=TED","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/af6ecfb3d1b63ff761d1f144f4ccf3af.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/af6ecfb3d1b63ff761d1f144f4ccf3af.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0}],"consumption":{"collectionCount":83,"shareCount":32,"replyCount":0},"resourceType":"video","slogan":null,"provider":{"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"},"category":"科技","author":{"id":142,"icon":"http://img.kaiyanapp.com/be0cdbff1cf1755c7aa78edee08db42b.jpeg","name":"TEDx","description":"干货演讲，分享人生。","link":"","latestReleaseTime":1546012807000,"videoNum":44,"adTrack":null,"follow":{"itemType":"author","itemId":142,"followed":false},"shield":{"itemType":"author","itemId":142,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true,"expert":false},"cover":{"feed":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/7c7e8d64d0aa930d52dbba2d914f936b.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=default&source=aliyun","thumbPlayUrl":null,"duration":804,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=142857","forWeibo":"http://www.eyepetizer.net/detail.html?vid=142857"},"releaseTime":1546012807000,"playInfo":[{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun","size":77725418},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=qcloud","size":77725418},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=ucloud","size":77725418}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun"}],"campaign":null,"waterMarks":null,"ad":false,"adTrack":null,"type":"NORMAL","titlePgc":null,"descriptionPgc":null,"remark":null,"ifLimitVideo":false,"searchWeight":0,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1546012807000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"2017 年 10 月，天体生物学家 Karen J. Meech 接到了 NASA 的电话，他们发现了一位来自另一星系的太空旅客 - 一个半英里长的星际彗星。这颗彗星最终被命名为「Oumuamua」，Meech 讲述了她的团队如何与时间赛跑，从地球探索这位太空旅客的故事。","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null,"src":null}
                 * tag : null
                 * id : 0
                 * adIndex : -1
                 */

                private String type;
                private DataBean data;
                private Object tag;
                private int id;
                private int adIndex;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public DataBean getData() {
                    return data;
                }

                public void setData(DataBean data) {
                    this.data = data;
                }

                public Object getTag() {
                    return tag;
                }

                public void setTag(Object tag) {
                    this.tag = tag;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getAdIndex() {
                    return adIndex;
                }

                public void setAdIndex(int adIndex) {
                    this.adIndex = adIndex;
                }

                public static class DataBean {
                    /**
                     * dataType : VideoBeanForClient
                     * id : 142857
                     * title : TED 演讲：来自另一星系的太空旅客
                     * description : 2017 年 10 月，天体生物学家 Karen J. Meech 接到了 NASA 的电话，他们发现了一位来自另一星系的太空旅客 - 一个半英里长的星际彗星。这颗彗星最终被命名为「Oumuamua」，Meech 讲述了她的团队如何与时间赛跑，从地球探索这位太空旅客的故事。
                     * library : DEFAULT
                     * tags : [{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=ç§\u0091æ\u0099®","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"IMPORTANT","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":546,"name":"宇宙","actionUrl":"eyepetizer://tag/546/?title=å®\u0087å®\u0099","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/7052c0f6e4267111b023d2541b1a7f07.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/7052c0f6e4267111b023d2541b1a7f07.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":38,"name":"演讲","actionUrl":"eyepetizer://tag/38/?title=æ¼\u0094è®²","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/bff90b4916df0e979f2dcdbd0fbd1467.png?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/bff90b4916df0e979f2dcdbd0fbd1467.png?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0},{"id":400,"name":"TED","actionUrl":"eyepetizer://tag/400/?title=TED","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/af6ecfb3d1b63ff761d1f144f4ccf3af.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/af6ecfb3d1b63ff761d1f144f4ccf3af.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL","childTagList":null,"childTagIdList":null,"communityIndex":0}]
                     * consumption : {"collectionCount":83,"shareCount":32,"replyCount":0}
                     * resourceType : video
                     * slogan : null
                     * provider : {"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"}
                     * category : 科技
                     * author : {"id":142,"icon":"http://img.kaiyanapp.com/be0cdbff1cf1755c7aa78edee08db42b.jpeg","name":"TEDx","description":"干货演讲，分享人生。","link":"","latestReleaseTime":1546012807000,"videoNum":44,"adTrack":null,"follow":{"itemType":"author","itemId":142,"followed":false},"shield":{"itemType":"author","itemId":142,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true,"expert":false}
                     * cover : {"feed":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/7c7e8d64d0aa930d52dbba2d914f936b.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
                     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=default&source=aliyun
                     * thumbPlayUrl : null
                     * duration : 804
                     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=142857","forWeibo":"http://www.eyepetizer.net/detail.html?vid=142857"}
                     * releaseTime : 1546012807000
                     * playInfo : [{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun","size":77725418},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=qcloud","size":77725418},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=ucloud","size":77725418}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun"}]
                     * campaign : null
                     * waterMarks : null
                     * ad : false
                     * adTrack : null
                     * type : NORMAL
                     * titlePgc : null
                     * descriptionPgc : null
                     * remark : null
                     * ifLimitVideo : false
                     * searchWeight : 0
                     * idx : 0
                     * shareAdTrack : null
                     * favoriteAdTrack : null
                     * webAdTrack : null
                     * date : 1546012807000
                     * promotion : null
                     * label : null
                     * labelList : []
                     * descriptionEditor : 2017 年 10 月，天体生物学家 Karen J. Meech 接到了 NASA 的电话，他们发现了一位来自另一星系的太空旅客 - 一个半英里长的星际彗星。这颗彗星最终被命名为「Oumuamua」，Meech 讲述了她的团队如何与时间赛跑，从地球探索这位太空旅客的故事。
                     * collected : false
                     * played : false
                     * subtitles : []
                     * lastViewTime : null
                     * playlists : null
                     * src : null
                     */

                    private String dataType;
                    private int id;
                    private String title;
                    private String description;
                    private String library;
                    private ConsumptionBean consumption;
                    private String resourceType;
                    private Object slogan;
                    private ProviderBean provider;
                    private String category;
                    private AuthorBean author;
                    private CoverBean cover;
                    private String playUrl;
                    private Object thumbPlayUrl;
                    private int duration;
                    private WebUrlBean webUrl;
                    private long releaseTime;
                    private Object campaign;
                    private Object waterMarks;
                    private boolean ad;
                    private Object adTrack;
                    private String type;
                    private Object titlePgc;
                    private Object descriptionPgc;
                    private Object remark;
                    private boolean ifLimitVideo;
                    private int searchWeight;
                    private int idx;
                    private Object shareAdTrack;
                    private Object favoriteAdTrack;
                    private Object webAdTrack;
                    private long date;
                    private Object promotion;
                    private Object label;
                    private String descriptionEditor;
                    private boolean collected;
                    private boolean played;
                    private Object lastViewTime;
                    private Object playlists;
                    private Object src;
                    private List<TagsBean> tags;
                    private List<PlayInfoBean> playInfo;
                    private List<?> labelList;
                    private List<?> subtitles;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getLibrary() {
                        return library;
                    }

                    public void setLibrary(String library) {
                        this.library = library;
                    }

                    public ConsumptionBean getConsumption() {
                        return consumption;
                    }

                    public void setConsumption(ConsumptionBean consumption) {
                        this.consumption = consumption;
                    }

                    public String getResourceType() {
                        return resourceType;
                    }

                    public void setResourceType(String resourceType) {
                        this.resourceType = resourceType;
                    }

                    public Object getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(Object slogan) {
                        this.slogan = slogan;
                    }

                    public ProviderBean getProvider() {
                        return provider;
                    }

                    public void setProvider(ProviderBean provider) {
                        this.provider = provider;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public AuthorBean getAuthor() {
                        return author;
                    }

                    public void setAuthor(AuthorBean author) {
                        this.author = author;
                    }

                    public CoverBean getCover() {
                        return cover;
                    }

                    public void setCover(CoverBean cover) {
                        this.cover = cover;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public Object getThumbPlayUrl() {
                        return thumbPlayUrl;
                    }

                    public void setThumbPlayUrl(Object thumbPlayUrl) {
                        this.thumbPlayUrl = thumbPlayUrl;
                    }

                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }

                    public WebUrlBean getWebUrl() {
                        return webUrl;
                    }

                    public void setWebUrl(WebUrlBean webUrl) {
                        this.webUrl = webUrl;
                    }

                    public long getReleaseTime() {
                        return releaseTime;
                    }

                    public void setReleaseTime(long releaseTime) {
                        this.releaseTime = releaseTime;
                    }

                    public Object getCampaign() {
                        return campaign;
                    }

                    public void setCampaign(Object campaign) {
                        this.campaign = campaign;
                    }

                    public Object getWaterMarks() {
                        return waterMarks;
                    }

                    public void setWaterMarks(Object waterMarks) {
                        this.waterMarks = waterMarks;
                    }

                    public boolean isAd() {
                        return ad;
                    }

                    public void setAd(boolean ad) {
                        this.ad = ad;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public Object getTitlePgc() {
                        return titlePgc;
                    }

                    public void setTitlePgc(Object titlePgc) {
                        this.titlePgc = titlePgc;
                    }

                    public Object getDescriptionPgc() {
                        return descriptionPgc;
                    }

                    public void setDescriptionPgc(Object descriptionPgc) {
                        this.descriptionPgc = descriptionPgc;
                    }

                    public Object getRemark() {
                        return remark;
                    }

                    public void setRemark(Object remark) {
                        this.remark = remark;
                    }

                    public boolean isIfLimitVideo() {
                        return ifLimitVideo;
                    }

                    public void setIfLimitVideo(boolean ifLimitVideo) {
                        this.ifLimitVideo = ifLimitVideo;
                    }

                    public int getSearchWeight() {
                        return searchWeight;
                    }

                    public void setSearchWeight(int searchWeight) {
                        this.searchWeight = searchWeight;
                    }

                    public int getIdx() {
                        return idx;
                    }

                    public void setIdx(int idx) {
                        this.idx = idx;
                    }

                    public Object getShareAdTrack() {
                        return shareAdTrack;
                    }

                    public void setShareAdTrack(Object shareAdTrack) {
                        this.shareAdTrack = shareAdTrack;
                    }

                    public Object getFavoriteAdTrack() {
                        return favoriteAdTrack;
                    }

                    public void setFavoriteAdTrack(Object favoriteAdTrack) {
                        this.favoriteAdTrack = favoriteAdTrack;
                    }

                    public Object getWebAdTrack() {
                        return webAdTrack;
                    }

                    public void setWebAdTrack(Object webAdTrack) {
                        this.webAdTrack = webAdTrack;
                    }

                    public long getDate() {
                        return date;
                    }

                    public void setDate(long date) {
                        this.date = date;
                    }

                    public Object getPromotion() {
                        return promotion;
                    }

                    public void setPromotion(Object promotion) {
                        this.promotion = promotion;
                    }

                    public Object getLabel() {
                        return label;
                    }

                    public void setLabel(Object label) {
                        this.label = label;
                    }

                    public String getDescriptionEditor() {
                        return descriptionEditor;
                    }

                    public void setDescriptionEditor(String descriptionEditor) {
                        this.descriptionEditor = descriptionEditor;
                    }

                    public boolean isCollected() {
                        return collected;
                    }

                    public void setCollected(boolean collected) {
                        this.collected = collected;
                    }

                    public boolean isPlayed() {
                        return played;
                    }

                    public void setPlayed(boolean played) {
                        this.played = played;
                    }

                    public Object getLastViewTime() {
                        return lastViewTime;
                    }

                    public void setLastViewTime(Object lastViewTime) {
                        this.lastViewTime = lastViewTime;
                    }

                    public Object getPlaylists() {
                        return playlists;
                    }

                    public void setPlaylists(Object playlists) {
                        this.playlists = playlists;
                    }

                    public Object getSrc() {
                        return src;
                    }

                    public void setSrc(Object src) {
                        this.src = src;
                    }

                    public List<TagsBean> getTags() {
                        return tags;
                    }

                    public void setTags(List<TagsBean> tags) {
                        this.tags = tags;
                    }

                    public List<PlayInfoBean> getPlayInfo() {
                        return playInfo;
                    }

                    public void setPlayInfo(List<PlayInfoBean> playInfo) {
                        this.playInfo = playInfo;
                    }

                    public List<?> getLabelList() {
                        return labelList;
                    }

                    public void setLabelList(List<?> labelList) {
                        this.labelList = labelList;
                    }

                    public List<?> getSubtitles() {
                        return subtitles;
                    }

                    public void setSubtitles(List<?> subtitles) {
                        this.subtitles = subtitles;
                    }

                    public static class ConsumptionBean {
                        /**
                         * collectionCount : 83
                         * shareCount : 32
                         * replyCount : 0
                         */

                        private int collectionCount;
                        private int shareCount;
                        private int replyCount;

                        public int getCollectionCount() {
                            return collectionCount;
                        }

                        public void setCollectionCount(int collectionCount) {
                            this.collectionCount = collectionCount;
                        }

                        public int getShareCount() {
                            return shareCount;
                        }

                        public void setShareCount(int shareCount) {
                            this.shareCount = shareCount;
                        }

                        public int getReplyCount() {
                            return replyCount;
                        }

                        public void setReplyCount(int replyCount) {
                            this.replyCount = replyCount;
                        }
                    }

                    public static class ProviderBean {
                        /**
                         * name : YouTube
                         * alias : youtube
                         * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                         */

                        private String name;
                        private String alias;
                        private String icon;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAlias() {
                            return alias;
                        }

                        public void setAlias(String alias) {
                            this.alias = alias;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }
                    }

                    public static class AuthorBean {
                        /**
                         * id : 142
                         * icon : http://img.kaiyanapp.com/be0cdbff1cf1755c7aa78edee08db42b.jpeg
                         * name : TEDx
                         * description : 干货演讲，分享人生。
                         * link :
                         * latestReleaseTime : 1546012807000
                         * videoNum : 44
                         * adTrack : null
                         * follow : {"itemType":"author","itemId":142,"followed":false}
                         * shield : {"itemType":"author","itemId":142,"shielded":false}
                         * approvedNotReadyVideoCount : 0
                         * ifPgc : true
                         * expert : false
                         */

                        private int id;
                        private String icon;
                        private String name;
                        private String description;
                        private String link;
                        private long latestReleaseTime;
                        private int videoNum;
                        private Object adTrack;
                        private FollowBeanX follow;
                        private ShieldBean shield;
                        private int approvedNotReadyVideoCount;
                        private boolean ifPgc;
                        private boolean expert;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDescription() {
                            return description;
                        }

                        public void setDescription(String description) {
                            this.description = description;
                        }

                        public String getLink() {
                            return link;
                        }

                        public void setLink(String link) {
                            this.link = link;
                        }

                        public long getLatestReleaseTime() {
                            return latestReleaseTime;
                        }

                        public void setLatestReleaseTime(long latestReleaseTime) {
                            this.latestReleaseTime = latestReleaseTime;
                        }

                        public int getVideoNum() {
                            return videoNum;
                        }

                        public void setVideoNum(int videoNum) {
                            this.videoNum = videoNum;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public FollowBeanX getFollow() {
                            return follow;
                        }

                        public void setFollow(FollowBeanX follow) {
                            this.follow = follow;
                        }

                        public ShieldBean getShield() {
                            return shield;
                        }

                        public void setShield(ShieldBean shield) {
                            this.shield = shield;
                        }

                        public int getApprovedNotReadyVideoCount() {
                            return approvedNotReadyVideoCount;
                        }

                        public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                            this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                        }

                        public boolean isIfPgc() {
                            return ifPgc;
                        }

                        public void setIfPgc(boolean ifPgc) {
                            this.ifPgc = ifPgc;
                        }

                        public boolean isExpert() {
                            return expert;
                        }

                        public void setExpert(boolean expert) {
                            this.expert = expert;
                        }

                        public static class FollowBeanX {
                            /**
                             * itemType : author
                             * itemId : 142
                             * followed : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean followed;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isFollowed() {
                                return followed;
                            }

                            public void setFollowed(boolean followed) {
                                this.followed = followed;
                            }
                        }

                        public static class ShieldBean {
                            /**
                             * itemType : author
                             * itemId : 142
                             * shielded : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean shielded;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isShielded() {
                                return shielded;
                            }

                            public void setShielded(boolean shielded) {
                                this.shielded = shielded;
                            }
                        }
                    }

                    public static class CoverBean {
                        /**
                         * feed : http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg
                         * detail : http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageMogr2/quality/60/format/jpg
                         * blurred : http://img.kaiyanapp.com/7c7e8d64d0aa930d52dbba2d914f936b.jpeg?imageMogr2/quality/60/format/jpg
                         * sharing : null
                         * homepage : http://img.kaiyanapp.com/13b4f950af427ec2459871718fb63e89.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                         */

                        private String feed;
                        private String detail;
                        private String blurred;
                        private Object sharing;
                        private String homepage;

                        public String getFeed() {
                            return feed;
                        }

                        public void setFeed(String feed) {
                            this.feed = feed;
                        }

                        public String getDetail() {
                            return detail;
                        }

                        public void setDetail(String detail) {
                            this.detail = detail;
                        }

                        public String getBlurred() {
                            return blurred;
                        }

                        public void setBlurred(String blurred) {
                            this.blurred = blurred;
                        }

                        public Object getSharing() {
                            return sharing;
                        }

                        public void setSharing(Object sharing) {
                            this.sharing = sharing;
                        }

                        public String getHomepage() {
                            return homepage;
                        }

                        public void setHomepage(String homepage) {
                            this.homepage = homepage;
                        }
                    }

                    public static class WebUrlBean {
                        /**
                         * raw : http://www.eyepetizer.net/detail.html?vid=142857
                         * forWeibo : http://www.eyepetizer.net/detail.html?vid=142857
                         */

                        private String raw;
                        private String forWeibo;

                        public String getRaw() {
                            return raw;
                        }

                        public void setRaw(String raw) {
                            this.raw = raw;
                        }

                        public String getForWeibo() {
                            return forWeibo;
                        }

                        public void setForWeibo(String forWeibo) {
                            this.forWeibo = forWeibo;
                        }
                    }

                    public static class TagsBean {
                        /**
                         * id : 44
                         * name : 科普
                         * actionUrl : eyepetizer://tag/44/?title=ç§æ®
                         * adTrack : null
                         * desc : null
                         * bgPicture : http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                         * headerImage : http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg
                         * tagRecType : IMPORTANT
                         * childTagList : null
                         * childTagIdList : null
                         * communityIndex : 0
                         */

                        private int id;
                        private String name;
                        private String actionUrl;
                        private Object adTrack;
                        private Object desc;
                        private String bgPicture;
                        private String headerImage;
                        private String tagRecType;
                        private Object childTagList;
                        private Object childTagIdList;
                        private int communityIndex;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getActionUrl() {
                            return actionUrl;
                        }

                        public void setActionUrl(String actionUrl) {
                            this.actionUrl = actionUrl;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public Object getDesc() {
                            return desc;
                        }

                        public void setDesc(Object desc) {
                            this.desc = desc;
                        }

                        public String getBgPicture() {
                            return bgPicture;
                        }

                        public void setBgPicture(String bgPicture) {
                            this.bgPicture = bgPicture;
                        }

                        public String getHeaderImage() {
                            return headerImage;
                        }

                        public void setHeaderImage(String headerImage) {
                            this.headerImage = headerImage;
                        }

                        public String getTagRecType() {
                            return tagRecType;
                        }

                        public void setTagRecType(String tagRecType) {
                            this.tagRecType = tagRecType;
                        }

                        public Object getChildTagList() {
                            return childTagList;
                        }

                        public void setChildTagList(Object childTagList) {
                            this.childTagList = childTagList;
                        }

                        public Object getChildTagIdList() {
                            return childTagIdList;
                        }

                        public void setChildTagIdList(Object childTagIdList) {
                            this.childTagIdList = childTagIdList;
                        }

                        public int getCommunityIndex() {
                            return communityIndex;
                        }

                        public void setCommunityIndex(int communityIndex) {
                            this.communityIndex = communityIndex;
                        }
                    }

                    public static class PlayInfoBean {
                        /**
                         * height : 720
                         * width : 1280
                         * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun","size":77725418},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=qcloud","size":77725418},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=ucloud","size":77725418}]
                         * name : 高清
                         * type : high
                         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun
                         */

                        private int height;
                        private int width;
                        private String name;
                        private String type;
                        private String url;
                        private List<UrlListBean> urlList;

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public List<UrlListBean> getUrlList() {
                            return urlList;
                        }

                        public void setUrlList(List<UrlListBean> urlList) {
                            this.urlList = urlList;
                        }

                        public static class UrlListBean {
                            /**
                             * name : aliyun
                             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=142857&resourceType=video&editionType=high&source=aliyun
                             * size : 77725418
                             */

                            private String name;
                            private String url;
                            private int size;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getSize() {
                                return size;
                            }

                            public void setSize(int size) {
                                this.size = size;
                            }
                        }
                    }
                }
            }
        }
    }
}
