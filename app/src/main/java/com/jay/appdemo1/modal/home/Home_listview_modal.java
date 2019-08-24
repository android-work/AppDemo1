package com.jay.appdemo1.modal.home;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/12/11.
 */

public class Home_listview_modal {

    private ArrayList<home1_listview> issueList;
    private String nextPageUrl;
    private long nextPublishTime;
    private String newestIssueType;
    private String dialog;

    public class home1_listview {

        private long releaseTime;
        private String type;
        private long date;
        private long publishTime;
        private ArrayList<home2_listview> itemList;
        private int count;

        public class home2_listview {

            private String type;
            private data data;
            private String tag;
            private int id;
            private int adIndex;

            public class data {

                private String dataType;
                private String text;
                private String font;
                private String adTrack;
                private int id;
                private String title;
                private String description;
                private String library;
                private ArrayList<home3_listview> tags;
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
                private ArrayList<home4_listview> playInfo;
                private String campaign;
                private String waterMarks;
                private boolean ad;
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
                private ArrayList playlists;
                private String src;

                public class home4_listview {

                    private int height;
                    private int width;
                    private ArrayList<home5_listview> urlList;
                    private String name;
                    private String type;
                    private String url;

                    public class home5_listview {

                        private String name;
                        private String url;
                        private long size;

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
                    }


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

                    public ArrayList<home5_listview> getUrlList() {
                        return urlList;
                    }

                    public void setUrlList(ArrayList<home5_listview> urlList) {
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
                }


                public class webUrl {

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


                public class cover {

                    private String feed;
                    private String detail;
                    private String blurred;
                    private String sharing;
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
                }


                public class provider {

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

                public class consumption {

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


                public class author {

                    private int id;
                    private String icon;
                    private String name;
                    private String description;
                    private String link;
                    private long latestReleaseTime;
                    private int videlNum;
                    private String adTrack;
                    private follow follow;
                    private shield shield;
                    private int approvedNotReadyVideoCount;
                    private boolean ifPgc;
                    private boolean expert;

                    public class shield {
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


                    public class follow {

                        private String itemType;
                        private int id;
                        private boolean followed;

                        public String getItemType() {
                            return itemType;
                        }

                        public void setItemType(String itemType) {
                            this.itemType = itemType;
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public boolean isFollowed() {
                            return followed;
                        }

                        public void setFollowed(boolean followed) {
                            this.followed = followed;
                        }
                    }


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

                    public int getVidelNum() {
                        return videlNum;
                    }

                    public void setVidelNum(int videlNum) {
                        this.videlNum = videlNum;
                    }

                    public String getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(String adTrack) {
                        this.adTrack = adTrack;
                    }

                    public follow getFollow() {
                        return follow;
                    }

                    public void setFollow(follow follow) {
                        this.follow = follow;
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

                    public shield getShield() {
                        return shield;
                    }

                    public void setShield(shield shield) {
                        this.shield = shield;
                    }
                }

                public class home3_listview {

                    private int id;
                    private String name;
                    private String actionUrl;
                    private String adTrack;
                    private String desc;
                    private String bgPicture;
                    private String headerImage;
                    private String tagRecType;
                    private ArrayList childTagList;
                    private ArrayList childTagIdList;
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

                    public ArrayList getChildTagList() {
                        return childTagList;
                    }

                    public void setChildTagList(ArrayList childTagList) {
                        this.childTagList = childTagList;
                    }

                    public ArrayList getChildTagIdList() {
                        return childTagIdList;
                    }

                    public void setChildTagIdList(ArrayList childTagIdList) {
                        this.childTagIdList = childTagIdList;
                    }

                    public int getCommunityIndex() {
                        return communityIndex;
                    }

                    public void setCommunityIndex(int communityIndex) {
                        this.communityIndex = communityIndex;
                    }
                }


                public String getDataType() {
                    return dataType;
                }

                public void setDataType(String dataType) {
                    this.dataType = dataType;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getFont() {
                    return font;
                }

                public void setFont(String font) {
                    this.font = font;
                }

                public String getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(String adTrack) {
                    this.adTrack = adTrack;
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

                public ArrayList<home3_listview> getTags() {
                    return tags;
                }

                public void setTags(ArrayList<home3_listview> tags) {
                    this.tags = tags;
                }

                public consumption getConsumption() {
                    return consumption;
                }

                public void setConsumption(consumption consumption) {
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

                public provider getProvider() {
                    return provider;
                }

                public void setProvider(provider provider) {
                    this.provider = provider;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public author getAuthor() {
                    return author;
                }

                public void setAuthor(author author) {
                    this.author = author;
                }

                public cover getCover() {
                    return cover;
                }

                public void setCover(cover cover) {
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

                public webUrl getWebUrl() {
                    return webUrl;
                }

                public void setWebUrl(webUrl webUrl) {
                    this.webUrl = webUrl;
                }

                public long getReleaseTime() {
                    return releaseTime;
                }

                public void setReleaseTime(long releaseTime) {
                    this.releaseTime = releaseTime;
                }

                public ArrayList<home4_listview> getPlayInfo() {
                    return playInfo;
                }

                public void setPlayInfo(ArrayList<home4_listview> playInfo) {
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

                public boolean isAd() {
                    return ad;
                }

                public void setAd(boolean ad) {
                    this.ad = ad;
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

                public ArrayList getPlaylists() {
                    return playlists;
                }

                public void setPlaylists(ArrayList playlists) {
                    this.playlists = playlists;
                }

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }


            }


            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public data getData() {
                return data;
            }

            public void setData(data data) {
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
        }


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

        public ArrayList<home2_listview> getItemList() {
            return itemList;
        }

        public void setItemList(ArrayList<home2_listview> itemList) {
            this.itemList = itemList;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    public ArrayList<home1_listview> getIssueList() {
        return issueList;
    }

    public void setIssueList(ArrayList<home1_listview> issueList) {
        this.issueList = issueList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
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



}
