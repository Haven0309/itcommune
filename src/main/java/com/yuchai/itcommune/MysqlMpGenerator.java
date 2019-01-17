package com.yuchai.itcommune;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Created by Haven
 * 2018/9/7 10:19
 *
 * @author Haven
 */
public class MysqlMpGenerator {

    public static void main(String[] args) {
        String outputDir = "D:\\workspace\\it-commune-service\\src\\main\\java";
        String basePackage = "com.yuchai.itcommune";
        String[] tableNames = new String[]{"project_user_money"};

        //1. 全局配置
        GlobalConfig gbConfig = new GlobalConfig();
        gbConfig.setActiveRecord(false) // 是否支持AR模式
                .setAuthor("Haven") // 作者
                .setOutputDir(outputDir) // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I（IEmployeeService）
                .setEnableCache(false)// 是否开启XML二级缓存
                .setBaseResultMap(true) //是否生成基本的mapper文件
                .setBaseColumnList(true); //基本的mapper文件是否生成基本的列

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://172.16.90.15:3306/commune?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("123456");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setRestControllerStyle(true) //设置Controller类型为RestController
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略-驼峰
//                .setColumnNaming(NamingStrategy.nochange)
//                .setTablePrefix("tbl_") //表前缀
                .setInclude(tableNames);  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(basePackage)
                .setXml("mapper.xml")
                .setMapper("mapper")
                .setEntity("entity")
                .setService("service")
                .setController("controller");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();

        ag.setGlobalConfig(gbConfig)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}
