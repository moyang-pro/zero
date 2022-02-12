package com.moyang.zero;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: moyang
 * @ClassName: GenerateCode
 * @Date: 2022/2/2 13:52
 * @Description: 自动代码生成工具
 * @Version: V1.0
 **/
public class GenerateCode {

	public static void main(String[] args) {

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// set freemarker 模板引擎
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		gc.setAuthor("moyang");
		gc.setOpen(false);
		//gc.setServiceName("%sService");	//去掉Service接口的首字母I
		//实体属性 Swagger2 注解
		gc.setSwagger2(true);
		//日期类型的字段使用哪个类型，默认是 java8的 日期类型，此处改为 java.util.date
		//gc.setDateType(DateType.ONLY_DATE);
		//是否覆盖 已存在文件，默认 false 不覆盖
		gc.setFileOverride(true);
		//mapper.xml 是否生成 ResultMap，默认 false 不生成
		gc.setBaseResultMap(false);
		//mapper.xml 是否生成 ColumnList，默认 false 不生成
		gc.setBaseColumnList(false);

		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://localhost:3306/zero?serverTimezone=UTC&tinyInt1isBit=false");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("Hualing.5251");
		dsc.setTypeConvert(new ITypeConvert() {
			@Override
			public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
				String t = fieldType.toLowerCase();
				if (t.contains("tinyint(1)")) {
					return DbColumnType.INTEGER;
				}
				return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
			}
		});
		mpg.setDataSource(dsc);

		// 设置路径
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.moyang.zero");
		//指定生成文件导入的包
		pc.setEntity("entity");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setController("controller");
		pc.setMapper("mapper");

		//指定生成文件的绝对路径
		Map<String, String> pathInfo  = new HashMap<>(6);
		String parentPath = "\\src\\main\\java\\com\\moyang\\zero";

		String entityPath = projectPath.concat("\\zero_domain").concat(parentPath).concat("\\entity");
		String mapperPath = projectPath.concat("\\zero_dao").concat(parentPath).concat("\\mapper");
		String xmlPath = projectPath.concat("\\zero_dao").concat("\\src\\main\\resources\\mapper");
		String servicePath = projectPath.concat("\\zero_service").concat(parentPath).concat("\\service");
		String serviceImplPath = projectPath.concat("\\zero_service").concat(parentPath).concat("\\service\\impl");
		String controllerPath = projectPath.concat("\\zero_web").concat(parentPath).concat("\\controller");

		pathInfo.put("entity_path",entityPath);
		pathInfo.put("mapper_path",mapperPath);
		pathInfo.put("xml_path",xmlPath);
		pathInfo.put("service_path",servicePath);
		pathInfo.put("service_impl_path",serviceImplPath);
		pathInfo.put("controller_path",controllerPath);
		pc.setPathInfo(pathInfo);
		mpg.setPackageInfo(pc);


		// 配置模板
		TemplateConfig templateConfig = new TemplateConfig();
		mpg.setTemplate(templateConfig);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
		//驼峰转
		strategy.setControllerMappingHyphenStyle(true);
		//表名
		Scanner scanner = new Scanner(System.in);
		System.out.print( "Please enter a table name : " );
		strategy.setInclude(scanner.next());
		strategy.setControllerMappingHyphenStyle(true);
		//是否启用 builder 模式;
		strategy.setChainModel(true);
		//如何去掉父类属性，下面有说明。
		strategy.setSuperEntityColumns("id","create_at","create_by","update_at","update_by","del_flag","remarks");
		// 字段注解：这里我们把表字段的注解打开，比如@TableField("car_name")
		//strategy.setEntityTableFieldAnnotationEnable(true);
		// 实体类的父类：如果有就配置，记得加包名
		strategy.setSuperEntityClass("com.moyang.zero.entity.Template");
		// 映射类的父类：如果有就配置，记得加包名
		strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
		// 服务接口的父类：如果有就配置，记得加包名
		strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
		// 服务实现类的父类：如果有就配置，记得加包名
		strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
		// 服务实现类的父类：如果有就配置，记得加包名
		strategy.setSuperControllerClass("com.moyang.zero.controller.TemplateController");

		mpg.setStrategy(strategy);

		// 自定义配置
		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				//自定义配置，在模版中cfg.superColumns 获取  这里解决子类会生成父类属性的问题，在模版里会用到该配置
				map.put("superColumns", this.getConfig().getStrategyConfig().getSuperEntityColumns());
				this.setMap(map);
			}
		};
		mpg.setCfg(cfg);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();


	}
}
