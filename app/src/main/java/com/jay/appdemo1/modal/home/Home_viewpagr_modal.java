package com.jay.appdemo1.modal.home;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by liuzhi on 2018/12/7.
 */

public class Home_viewpagr_modal {
    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(String nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public String getNewestIssueType() {
        return newestIssueType;
    }

    public void setNewestIssueType(String newestIssueType) {
        this.newestIssueType = newestIssueType;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public ArrayList<home1> getIssueList() {
        return issueList;
    }

    public void setIssueList(ArrayList<home1> issueList) {
        this.issueList = issueList;
    }

    private String nextPageUrl;
    private String nextPublishTime;
    private String newestIssueType;
    private String dialog;
    private ArrayList<home1> issueList=new ArrayList();

    public class home1{
        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public ArrayList<home2> getItemList() {
            return itemList;
        }

        public void setItemList(ArrayList<home2> itemList) {
            this.itemList = itemList;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        private long releaseTime;
        private String type;
        private long date;
        private long publishTime;
        private ArrayList<home2> itemList = new ArrayList();
        private int count;

        public class home2{
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public home2.data getData() {
                return data;
            }

            public void setData(home2.data data) {
                this.data = data;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
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

            private String type;
            private data data;
            private String tag;//首页的轮播模块
            private int id;
            private int adIndex;

            public class data{
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

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public boolean isShade() {
                    return shade;
                }

                public void setShade(boolean shade) {
                    this.shade = shade;
                }

                public String getHeader() {
                    return header;
                }

                public void setHeader(String header) {
                    this.header = header;
                }

                public boolean isAutoPlay() {
                    return autoPlay;
                }

                public void setAutoPlay(boolean autoPlay) {
                    this.autoPlay = autoPlay;
                }

                public String getLibrary() {
                    return library;
                }

                public void setLibrary(String library) {
                    this.library = library;
                }

                public ArrayList<home3> getTags() {
                    return tags;
                }

                public void setTags(ArrayList<home3> tags) {
                    this.tags = tags;
                }

                public home2.data.consumption getConsumption() {
                    return consumption;
                }

                public void setConsumption(home2.data.consumption consumption) {
                    this.consumption = consumption;
                }

                public String getResourceType() {
                    return resourceType;
                }

                public void setResourceType(String resourceType) {
                    this.resourceType = resourceType;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public home2.data.provider getProvider() {
                    return provider;
                }

                public void setProvider(home2.data.provider provider) {
                    this.provider = provider;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public home2.data.author getAuthor() {
                    return author;
                }

                public void setAuthor(home2.data.author author) {
                    this.author = author;
                }

                public home2.data.cover getCover() {
                    return cover;
                }

                public void setCover(home2.data.cover cover) {
                    this.cover = cover;
                }

                public String getPlayUrl() {
                    return playUrl;
                }

                public void setPlayUrl(String playUrl) {
                    this.playUrl = playUrl;
                }

                public String getThumbPlayUrl() {
                    return thumbPlayUrl;
                }

                public void setThumbPlayUrl(String thumbPlayUrl) {
                    this.thumbPlayUrl = thumbPlayUrl;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }

                public home2.data.webUrl getWebUrl() {
                    return webUrl;
                }

                public void setWebUrl(home2.data.webUrl webUrl) {
                    this.webUrl = webUrl;
                }

                public long getReleaseTime() {
                    return releaseTime;
                }

                public void setReleaseTime(long releaseTime) {
                    this.releaseTime = releaseTime;
                }

                public ArrayList<home4> getPlayInfo() {
                    return playInfo;
                }

                public void setPlayInfo(ArrayList<home4> playInfo) {
                    this.playInfo = playInfo;
                }

                public String getCampaign() {
                    return campaign;
                }

                public void setCampaign(String campaign) {
                    this.campaign = campaign;
                }

                public String getWaterMarks() {
                    return waterMarks;
                }

                public void setWaterMarks(String waterMarks) {
                    this.waterMarks = waterMarks;
                }

                public String getAd() {
                    return ad;
                }

                public void setAd(String ad) {
                    this.ad = ad;
                }

                public String getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(String adTrack) {
                    this.adTrack = adTrack;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getTitlePgc() {
                    return titlePgc;
                }

                public void setTitlePgc(String titlePgc) {
                    this.titlePgc = titlePgc;
                }

                public String getDescriptionPgc() {
                    return descriptionPgc;
                }

                public void setDescriptionPgc(String descriptionPgc) {
                    this.descriptionPgc = descriptionPgc;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
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

                public String getShareAdTrack() {
                    return shareAdTrack;
                }

                public void setShareAdTrack(String shareAdTrack) {
                    this.shareAdTrack = shareAdTrack;
                }

                public String getFavoriteAdTrack() {
                    return favoriteAdTrack;
                }

                public void setFavoriteAdTrack(String favoriteAdTrack) {
                    this.favoriteAdTrack = favoriteAdTrack;
                }

                public String getWebAdTrack() {
                    return webAdTrack;
                }

                public void setWebAdTrack(String webAdTrack) {
                    this.webAdTrack = webAdTrack;
                }

                public long getDate() {
                    return date;
                }

                public void setDate(long date) {
                    this.date = date;
                }

                public String getPromotion() {
                    return promotion;
                }

                public void setPromotion(String promotion) {
                    this.promotion = promotion;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public ArrayList getLabelList() {
                    return labelList;
                }

                public void setLabelList(ArrayList labelList) {
                    this.labelList = labelList;
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

                public ArrayList getSubtitles() {
                    return subtitles;
                }

                public void setSubtitles(ArrayList subtitles) {
                    this.subtitles = subtitles;
                }

                public String getLastViewTime() {
                    return lastViewTime;
                }

                public void setLastViewTime(String lastViewTime) {
                    this.lastViewTime = lastViewTime;
                }

                public String getPlaylists() {
                    return playlists;
                }

                public void setPlaylists(String playlists) {
                    this.playlists = playlists;
                }

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                private String dataType;
                private int id;
                private String title;
                private String description;
                private String image;
                private String actionUrl;
                private boolean shade;
                private String header;
                private boolean autoPlay;
                private String library;
                private ArrayList<home3> tags;
                private consumption consumption;
                private String resourceType;
                private String slogan;
                private provider provider;
                private String category;
                private author author;
                private cover cover;
                private String playUrl;
                private String thumbPlayUrl;
                private int duration;
                private webUrl webUrl;
                private long releaseTime;
                private ArrayList<home4> playInfo;
                private String campaign;
                private String waterMarks;
                private String ad;
                private String adTrack;
                private String type;
                private String titlePgc;
                private String descriptionPgc;
                private String remark;
                private boolean ifLimitVideo;
                private int searchWeight;
                private int idx;
                private String shareAdTrack;
                private String favoriteAdTrack;
                private String webAdTrack;
                private long date;
                private String promotion;
                private String label;
                private ArrayList labelList;
                private String descriptionEditor;
                private boolean collected;
                private boolean played;
                private ArrayList subtitles;
                private String lastViewTime;
                private String playlists;
                private String src;

                public class home3{

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

                    public String getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(String adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
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

                    public String getChildTagList() {
                        return childTagList;
                    }

                    public void setChildTagList(String childTagList) {
                        this.childTagList = childTagList;
                    }

                    public String getChildTagIdList() {
                        return childTagIdList;
                    }

                    public void setChildTagIdList(String childTagIdList) {
                        this.childTagIdList = childTagIdList;
                    }

                    public int getCommunityIndex() {
                        return communityIndex;
                    }

                    public void setCommunityIndex(int communityIndex) {
                        this.communityIndex = communityIndex;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    private int id;
                    private String name;
                    private String actionUrl;
                    private String adTrack;
                    private String desc;
                    private String bgPicture;
                    private String headerImage;
                    private String tagRecType;
                    private String childTagList;
                    private String childTagIdList;
                    private int communityIndex;
                }

                public class consumption{
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

                    private int collectionCount;
                    private int shareCount;
                    private int replyCount;
                }

                public class provider{

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

                    private String name;
                    private String alias;
                    private String icon;
                }

                public class author{
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

                    public String getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(String adTrack) {
                        this.adTrack = adTrack;
                    }

                    public home2.data.author.follow getFollow() {
                        return follow;
                    }

                    public void setFollow(home2.data.author.follow follow) {
                        this.follow = follow;
                    }

                    public home2.data.author.shield getShield() {
                        return shield;
                    }

                    public void setShield(home2.data.author.shield shield) {
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

                    private int id;
                    private String icon;
                    private String name;
                    private String description;
                    private String link;
                    private long latestReleaseTime;
                    private int videoNum;
                    private String adTrack;
                    private follow follow;
                    private shield shield;
                    private int approvedNotReadyVideoCount;
                    private boolean ifPgc;
                    private boolean expert;

                    public class follow{
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

                        private String itemType;
                        private int itemId;
                        private boolean followed;
                    }

                    public class shield{
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

                        private String itemType;
                        private int itemId;
                        private boolean shielded;
                    }
                }

                public class cover{
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

                    public String getSharing() {
                        return sharing;
                    }

                    public void setSharing(String sharing) {
                        this.sharing = sharing;
                    }

                    public String getHomepage() {
                        return homepage;
                    }

                    public void setHomepage(String homepage) {
                        this.homepage = homepage;
                    }

                    private String feed;
                    private String detail;
                    private String blurred;
                    private String sharing;
                    private String homepage;
                }

                public class webUrl{
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

                    private String raw;
                    private String forWeibo;
                }

                public class home4{
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

                    public ArrayList<home5> getUrlList() {
                        return urlList;
                    }

                    public void setUrlList(ArrayList<home5> urlList) {
                        this.urlList = urlList;
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

                    private int height;
                    private int width;
                    private ArrayList<home5> urlList;
                    private String name;
                    private String type;
                    private String url;

                    public class home5{
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

                        public long getSize() {
                            return size;
                        }

                        public void setSize(long size) {
                            this.size = size;
                        }

                        private String name;
                        private String url;
                        private long size;
                    }
                }
            }
        }
    }
}


