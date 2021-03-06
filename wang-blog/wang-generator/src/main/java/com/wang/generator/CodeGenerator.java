package com.wang.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

// 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    // 生成的代码放到哪个工程中
    private static final String PROJECT_NAME = "wang-system";

    // 数据库名称
    private static final String DATABASE_NAME = "blog-system";

    // 父包名
    private static final String PARENT_MODULE_NAME = "com.wang";

    // 子包名
    private static final String MODULE_NAME = "system";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/"+ DATABASE_NAME +"?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/";
        gc.setOutputDir(projectPath + PROJECT_NAME +"/src/main/java");
        gc.setIdType(IdType.ASSIGN_ID); // 分布式id
        gc.setAuthor("超人"); // 作者
        gc.setFileOverride(true); // 覆盖现有的
        gc.setOpen(false); // 是否生成后打开
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名
        pc.setParent(PARENT_MODULE_NAME);
        // com.wang.article.controller
        pc.setController(MODULE_NAME + ".controller");
        pc.setService(MODULE_NAME + ".service");
        pc.setServiceImpl(MODULE_NAME + ".service.impl");
        pc.setMapper(MODULE_NAME + ".dao");
        pc.setXml(MODULE_NAME + ".dao.xml");
        //实体类存储包名 com.wang.entities
        pc.setEntity(MODULE_NAME  + ".dto.po");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名驼峰化
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名驼峰化
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 使用lombok
        strategy.setEntityLombokModel(true);
        // 实体类的是否实现接口Serializable
        strategy.setEntitySerialVersionUID(false);
        // @RestController
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        // 去掉表的前缀 wang_article => article
        strategy.setTablePrefix("");
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

}