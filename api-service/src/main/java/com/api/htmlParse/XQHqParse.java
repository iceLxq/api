package com.api.htmlParse;

import com.api.domain.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李显琪 on 2021/9/17.
 */

public class XQHqParse {

    public static List<Category> parseHtml(String data) {
        String urlPre = "https://xueqiu.com/hq";
        Document document = Jsoup.parse(htmlToString());
        List<Category> shareCategoryList = new ArrayList<>();
        Elements elementsByClass = document.getElementsByAttribute("href");
        for (Element element : elementsByClass) {
            String url = urlPre + element.attr("href");
            String title = element.attr("title");
            String code = element.attr("data-level2code");
            String name = element.text();
            Category shareCategory = new Category(name, url, code);
            shareCategoryList.add(shareCategory);
        }
        return shareCategoryList;
    }

    private static String htmlToString() {
        String html = "<div class=\"third-nav\">\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">B</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_23&firstName=1&secondName=1_2&level2code=S2701\" title=半导体 data-type=\"undefined\" data-level2code=\"S2701\">半导体</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_32&firstName=1&secondName=1_2&level2code=S3301\" title=白色家电 data-type=\"undefined\" data-level2code=\"S3301\">白色家电</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_39&firstName=1&secondName=1_2&level2code=S3602\" title=包装印刷 data-type=\"undefined\" data-level2code=\"S3602\">包装印刷</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_73&firstName=1&secondName=1_2&level2code=S4902\" title=保险 data-type=\"undefined\" data-level2code=\"S4902\">保险</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_77&firstName=1&secondName=1_2&level2code=S6102\" title=玻璃制造 data-type=\"undefined\" data-level2code=\"S6102\">玻璃制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">C</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_6&firstName=1&secondName=1_2&level2code=S1107\" title=畜禽养殖 data-type=\"undefined\" data-level2code=\"S1107\">畜禽养殖</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_11&firstName=1&secondName=1_2&level2code=S2104\" title=采掘服务 data-type=\"undefined\" data-level2code=\"S2104\">采掘服务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_69&firstName=1&secondName=1_2&level2code=S4604\" title=餐饮 data-type=\"undefined\" data-level2code=\"S4604\">餐饮</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_96&firstName=1&secondName=1_2&level2code=S6504\" title=船舶制造 data-type=\"undefined\" data-level2code=\"S6504\">船舶制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">D</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_7&firstName=1&secondName=1_2&level2code=S1108\" title=动物保健 data-type=\"undefined\" data-level2code=\"S1108\">动物保健</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_27&firstName=1&secondName=1_2&level2code=S2705\" title=电子制造 data-type=\"undefined\" data-level2code=\"S2705\">电子制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_48&firstName=1&secondName=1_2&level2code=S4101\" title=电力 data-type=\"undefined\" data-level2code=\"S4101\">电力</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_74&firstName=1&secondName=1_2&level2code=S4903\" title=多元金融 data-type=\"undefined\" data-level2code=\"S4903\">多元金融</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_84&firstName=1&secondName=1_2&level2code=S6301\" title=电机 data-type=\"undefined\" data-level2code=\"S6301\">电机</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_85&firstName=1&secondName=1_2&level2code=S6302\" title=电气自动化设备 data-type=\"undefined\" data-level2code=\"S6302\">电气自动化设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_86&firstName=1&secondName=1_2&level2code=S6303\" title=电源设备 data-type=\"undefined\" data-level2code=\"S6303\">电源设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_95&firstName=1&secondName=1_2&level2code=S6503\" title=地面兵装 data-type=\"undefined\" data-level2code=\"S6503\">地面兵装</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">F</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_36&firstName=1&secondName=1_2&level2code=S3501\" title=纺织制造 data-type=\"undefined\" data-level2code=\"S3501\">纺织制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_37&firstName=1&secondName=1_2&level2code=S3502\" title=服装家纺 data-type=\"undefined\" data-level2code=\"S3502\">服装家纺</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_60&firstName=1&secondName=1_2&level2code=S4301\" title=房地产开发 data-type=\"undefined\" data-level2code=\"S4301\">房地产开发</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_79&firstName=1&secondName=1_2&level2code=S6201\" title=房屋建设 data-type=\"undefined\" data-level2code=\"S6201\">房屋建设</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">G</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_18&firstName=1&secondName=1_2&level2code=S2301\" title=钢铁 data-type=\"undefined\" data-level2code=\"S2301\">钢铁</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_20&firstName=1&secondName=1_2&level2code=S2403\" title=工业金属 data-type=\"undefined\" data-level2code=\"S2403\">工业金属</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_25&firstName=1&secondName=1_2&level2code=S2703\" title=光学光电子 data-type=\"undefined\" data-level2code=\"S2703\">光学光电子</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_52&firstName=1&secondName=1_2&level2code=S4201\" title=港口 data-type=\"undefined\" data-level2code=\"S4201\">港口</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_53&firstName=1&secondName=1_2&level2code=S4202\" title=高速公路 data-type=\"undefined\" data-level2code=\"S4202\">高速公路</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_54&firstName=1&secondName=1_2&level2code=S4203\" title=公交 data-type=\"undefined\" data-level2code=\"S4203\">公交</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_87&firstName=1&secondName=1_2&level2code=S6304\" title=高低压设备 data-type=\"undefined\" data-level2code=\"S6304\">高低压设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">H</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_13&firstName=1&secondName=1_2&level2code=S2202\" title=化学原料 data-type=\"undefined\" data-level2code=\"S2202\">化学原料</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_14&firstName=1&secondName=1_2&level2code=S2203\" title=化学制品 data-type=\"undefined\" data-level2code=\"S2203\">化学制品</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_15&firstName=1&secondName=1_2&level2code=S2204\" title=化学纤维 data-type=\"undefined\" data-level2code=\"S2204\">化学纤维</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_21&firstName=1&secondName=1_2&level2code=S2404\" title=黄金 data-type=\"undefined\" data-level2code=\"S2404\">黄金</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_42&firstName=1&secondName=1_2&level2code=S3701\" title=化学制药 data-type=\"undefined\" data-level2code=\"S3701\">化学制药</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_51&firstName=1&secondName=1_2&level2code=S4104\" title=环保工程及服务 data-type=\"undefined\" data-level2code=\"S4104\">环保工程及服务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_55&firstName=1&secondName=1_2&level2code=S4204\" title=航空运输 data-type=\"undefined\" data-level2code=\"S4204\">航空运输</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_57&firstName=1&secondName=1_2&level2code=S4206\" title=航运 data-type=\"undefined\" data-level2code=\"S4206\">航运</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_93&firstName=1&secondName=1_2&level2code=S6501\" title=航天装备 data-type=\"undefined\" data-level2code=\"S6501\">航天装备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_94&firstName=1&secondName=1_2&level2code=S6502\" title=航空装备 data-type=\"undefined\" data-level2code=\"S6502\">航空装备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_101&firstName=1&secondName=1_2&level2code=S7203\" title=互联网传媒 data-type=\"undefined\" data-level2code=\"S7203\">互联网传媒</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">J</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_19&firstName=1&secondName=1_2&level2code=S2402\" title=金属非金属新材料 data-type=\"undefined\" data-level2code=\"S2402\">金属非金属新材...</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_40&firstName=1&secondName=1_2&level2code=S3603\" title=家用轻工 data-type=\"undefined\" data-level2code=\"S3603\">家用轻工</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_56&firstName=1&secondName=1_2&level2code=S4205\" title=机场 data-type=\"undefined\" data-level2code=\"S4205\">机场</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_66&firstName=1&secondName=1_2&level2code=S4601\" title=景点 data-type=\"undefined\" data-level2code=\"S4601\">景点</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_67&firstName=1&secondName=1_2&level2code=S4602\" title=酒店 data-type=\"undefined\" data-level2code=\"S4602\">酒店</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_81&firstName=1&secondName=1_2&level2code=S6203\" title=基础建设 data-type=\"undefined\" data-level2code=\"S6203\">基础建设</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_91&firstName=1&secondName=1_2&level2code=S6404\" title=金属制品 data-type=\"undefined\" data-level2code=\"S6404\">金属制品</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_97&firstName=1&secondName=1_2&level2code=S7101\" title=计算机设备 data-type=\"undefined\" data-level2code=\"S7101\">计算机设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_98&firstName=1&secondName=1_2&level2code=S7102\" title=计算机应用 data-type=\"undefined\" data-level2code=\"S7102\">计算机应用</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">L</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_2&firstName=1&secondName=1_2&level2code=S1103\" title=林业 data-type=\"undefined\" data-level2code=\"S1103\">林业</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_68&firstName=1&secondName=1_2&level2code=S4603\" title=旅游综合 data-type=\"undefined\" data-level2code=\"S4603\">旅游综合</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">M</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_9&firstName=1&secondName=1_2&level2code=S2102\" title=煤炭开采 data-type=\"undefined\" data-level2code=\"S2102\">煤炭开采</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_62&firstName=1&secondName=1_2&level2code=S4502\" title=贸易 data-type=\"undefined\" data-level2code=\"S4502\">贸易</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">N</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_4&firstName=1&secondName=1_2&level2code=S1105\" title=农产品加工 data-type=\"undefined\" data-level2code=\"S1105\">农产品加工</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_5&firstName=1&secondName=1_2&level2code=S1106\" title=农业综合 data-type=\"undefined\" data-level2code=\"S1106\">农业综合</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">Q</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_10&firstName=1&secondName=1_2&level2code=S2103\" title=其他采掘 data-type=\"undefined\" data-level2code=\"S2103\">其他采掘</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_26&firstName=1&secondName=1_2&level2code=S2704\" title=其他电子 data-type=\"undefined\" data-level2code=\"S2704\">其他电子</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_28&firstName=1&secondName=1_2&level2code=S2801\" title=汽车整车 data-type=\"undefined\" data-level2code=\"S2801\">汽车整车</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_29&firstName=1&secondName=1_2&level2code=S2802\" title=汽车零部件 data-type=\"undefined\" data-level2code=\"S2802\">汽车零部件</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_30&firstName=1&secondName=1_2&level2code=S2803\" title=汽车服务 data-type=\"undefined\" data-level2code=\"S2803\">汽车服务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_31&firstName=1&secondName=1_2&level2code=S2804\" title=其他交运设备 data-type=\"undefined\" data-level2code=\"S2804\">其他交运设备</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_41&firstName=1&secondName=1_2&level2code=S3604\" title=其他轻工制造 data-type=\"undefined\" data-level2code=\"S3604\">其他轻工制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_70&firstName=1&secondName=1_2&level2code=S4605\" title=其他休闲服务 data-type=\"undefined\" data-level2code=\"S4605\">其他休闲服务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_78&firstName=1&secondName=1_2&level2code=S6103\" title=其他建材 data-type=\"undefined\" data-level2code=\"S6103\">其他建材</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">R</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_50&firstName=1&secondName=1_2&level2code=S4103\" title=燃气 data-type=\"undefined\" data-level2code=\"S4103\">燃气</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">S</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_3&firstName=1&secondName=1_2&level2code=S1104\" title=饲料 data-type=\"undefined\" data-level2code=\"S1104\">饲料</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_8&firstName=1&secondName=1_2&level2code=S2101\" title=石油开采 data-type=\"undefined\" data-level2code=\"S2101\">石油开采</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_12&firstName=1&secondName=1_2&level2code=S2201\" title=石油化工 data-type=\"undefined\" data-level2code=\"S2201\">石油化工</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_16&firstName=1&secondName=1_2&level2code=S2205\" title=塑料 data-type=\"undefined\" data-level2code=\"S2205\">塑料</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_33&firstName=1&secondName=1_2&level2code=S3302\" title=视听器材 data-type=\"undefined\" data-level2code=\"S3302\">视听器材</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_35&firstName=1&secondName=1_2&level2code=S3404\" title=食品加工 data-type=\"undefined\" data-level2code=\"S3404\">食品加工</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_44&firstName=1&secondName=1_2&level2code=S3703\" title=生物制品 data-type=\"undefined\" data-level2code=\"S3703\">生物制品</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_49&firstName=1&secondName=1_2&level2code=S4102\" title=水务 data-type=\"undefined\" data-level2code=\"S4102\">水务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_65&firstName=1&secondName=1_2&level2code=S4505\" title=商业物业经营 data-type=\"undefined\" data-level2code=\"S4505\">商业物业经营</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_76&firstName=1&secondName=1_2&level2code=S6101\" title=水泥制造 data-type=\"undefined\" data-level2code=\"S6101\">水泥制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">T</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_58&firstName=1&secondName=1_2&level2code=S4207\" title=铁路运输 data-type=\"undefined\" data-level2code=\"S4207\">铁路运输</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_88&firstName=1&secondName=1_2&level2code=S6401\" title=通用机械 data-type=\"undefined\" data-level2code=\"S6401\">通用机械</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_102&firstName=1&secondName=1_2&level2code=S7301\" title=通信运营 data-type=\"undefined\" data-level2code=\"S7301\">通信运营</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_103&firstName=1&secondName=1_2&level2code=S7302\" title=通信设备 data-type=\"undefined\" data-level2code=\"S7302\">通信设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">W</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_59&firstName=1&secondName=1_2&level2code=S4208\" title=物流 data-type=\"undefined\" data-level2code=\"S4208\">物流</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_99&firstName=1&secondName=1_2&level2code=S7201\" title=文化传媒 data-type=\"undefined\" data-level2code=\"S7201\">文化传媒</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">X</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_17&firstName=1&secondName=1_2&level2code=S2206\" title=橡胶 data-type=\"undefined\" data-level2code=\"S2206\">橡胶</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_22&firstName=1&secondName=1_2&level2code=S2405\" title=稀有金属 data-type=\"undefined\" data-level2code=\"S2405\">稀有金属</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">Y</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_1&firstName=1&secondName=1_2&level2code=S1102\" title=渔业 data-type=\"undefined\" data-level2code=\"S1102\">渔业</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_24&firstName=1&secondName=1_2&level2code=S2702\" title=元件 data-type=\"undefined\" data-level2code=\"S2702\">元件</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_34&firstName=1&secondName=1_2&level2code=S3403\" title=饮料制造 data-type=\"undefined\" data-level2code=\"S3403\">饮料制造</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_45&firstName=1&secondName=1_2&level2code=S3704\" title=医药商业 data-type=\"undefined\" data-level2code=\"S3704\">医药商业</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_46&firstName=1&secondName=1_2&level2code=S3705\" title=医疗器械 data-type=\"undefined\" data-level2code=\"S3705\">医疗器械</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_47&firstName=1&secondName=1_2&level2code=S3706\" title=医疗服务 data-type=\"undefined\" data-level2code=\"S3706\">医疗服务</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_61&firstName=1&secondName=1_2&level2code=S4302\" title=园区开发 data-type=\"undefined\" data-level2code=\"S4302\">园区开发</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_63&firstName=1&secondName=1_2&level2code=S4503\" title=一般零售 data-type=\"undefined\" data-level2code=\"S4503\">一般零售</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_71&firstName=1&secondName=1_2&level2code=S4801\" title=银行 data-type=\"undefined\" data-level2code=\"S4801\">银行</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_83&firstName=1&secondName=1_2&level2code=S6205\" title=园林工程 data-type=\"undefined\" data-level2code=\"S6205\">园林工程</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_90&firstName=1&secondName=1_2&level2code=S6403\" title=仪器仪表 data-type=\"undefined\" data-level2code=\"S6403\">仪器仪表</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_92&firstName=1&secondName=1_2&level2code=S6405\" title=运输设备 data-type=\"undefined\" data-level2code=\"S6405\">运输设备</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_100&firstName=1&secondName=1_2&level2code=S7202\" title=营销传播 data-type=\"undefined\" data-level2code=\"S7202\">营销传播</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\">Z</span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_0&firstName=1&secondName=1_2&level2code=S1101\" title=种植业 data-type=\"undefined\" data-level2code=\"S1101\">种植业</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_38&firstName=1&secondName=1_2&level2code=S3601\" title=造纸 data-type=\"undefined\" data-level2code=\"S3601\">造纸</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_43&firstName=1&secondName=1_2&level2code=S3702\" title=中药 data-type=\"undefined\" data-level2code=\"S3702\">中药</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_64&firstName=1&secondName=1_2&level2code=S4504\" title=专业零售 data-type=\"undefined\" data-level2code=\"S4504\">专业零售</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_72&firstName=1&secondName=1_2&level2code=S4901\" title=证券 data-type=\"undefined\" data-level2code=\"S4901\">证券</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                    <ul>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_75&firstName=1&secondName=1_2&level2code=S5101\" title=综合 data-type=\"undefined\" data-level2code=\"S5101\">综合</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_80&firstName=1&secondName=1_2&level2code=S6202\" title=装修装饰 data-type=\"undefined\" data-level2code=\"S6202\">装修装饰</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_82&firstName=1&secondName=1_2&level2code=S6204\" title=专业工程 data-type=\"undefined\" data-level2code=\"S6204\">专业工程</a>\n" +
                "                                        </li>\n" +
                "                                        <li>\n" +
                "                                            <span class=\"initial\"> </span>\n" +
                "                                            <a href=\"#exchange=CN&plate=1_2_89&firstName=1&secondName=1_2&level2code=S6402\" title=专用设备 data-type=\"undefined\" data-level2code=\"S6402\">专用设备</a>\n" +
                "                                        </li>\n" +
                "                                    </ul>\n" +
                "                                </div>";
        return html;
    }


}
