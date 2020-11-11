package com.graduation.bookreader.enums;

/**
 * Description:
 * 书籍类型
 * <p>
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 22:13
 */
public enum BookTypeEnum {
    /**
     * 玄幻奇幻
     */
    FANTASY("玄幻奇幻", 1),

    /**
     * 武侠仙侠
     */
    MARTIAL_ARTS_IMMORTAL("武侠仙侠", 2),

    /**
     * 女频言情
     */
    LOVE_WITH_WOMEN("女频言情", 3),

    /**
     * 现代都市
     */
    MODERN_CITY("现代都市", 4),

    /**
     * 历史军事
     */
    HISTORICAL_MILITARY("历史军事", 5),

    /**
     * 游戏竞技
     */
    GAME_COMPETITION("游戏竞技", 6),

    /**
     * 科幻灵异
     */
    SCIENCE_FICTION_SUPERNATURAL("科幻灵异", 7),

    /**
     * 美文同人
     */
    AMERICAN_COLLEAGUES("美文同人", 8),

    /**
     * 剧本教程
     */
    SCRIPT_TUTORIAL("剧本教程", 9);

    /**
     * 中文解释
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;

    BookTypeEnum(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public static BookTypeEnum getTypeByType(Integer type){
        for (BookTypeEnum value : BookTypeEnum.values()) {
            if(value.type.equals(type)){
                return value;
            }
        }
        return null;
    }

    public static String getName(Integer type){
        BookTypeEnum typeByType = getTypeByType(type);
        if(typeByType == null){
            return null;
        }
        return typeByType.name;
    }
}
