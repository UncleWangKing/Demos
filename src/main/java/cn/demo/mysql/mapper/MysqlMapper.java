package cn.demo.mysql.mapper;

import cn.demo.mysql.domain.MysqlBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MysqlMapper {

	List<MysqlBean> getAll();

	Boolean batchInsert(@Param("list") List<MysqlBean> list);
}
