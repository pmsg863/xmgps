package ${attrs.packageName};

import ${attrs.packageName}.${attrs.className?cap_first}Response;
import ${attrs.packageName}.${attrs.className?cap_first}Request;
import javax.ws.rs.*;
import com.xmgps.framework.restapi.parser.SQLModelManager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("/${attrs.path}")
@Produces("application/json")
@Consumes("application/json")
public class ${attrs.className?cap_first}Query {

<#if sqlmodel.type == 'INSERT' >
@${attrs.apiType}
@Path("${attrs.subPath}")
public int get${attrs.apiName?cap_first}(${attrs.className?cap_first}Request input){
Connection conn = SQLModelManager.getConnection();

QueryRunner qRunner = new QueryRunner();

try {
return qRunner.update(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),SQLModelManager.getFieldArray(input));
} catch (SQLException e) {
e.printStackTrace();
} finally {
DbUtils.closeQuietly(conn);
}
return -1;
}
<#elseif sqlmodel.type == 'UPDATE' >
@${attrs.apiType}
@Path("${attrs.subPath}")
public int get${attrs.apiName?cap_first}(${attrs.className?cap_first}Request input){
Connection conn = SQLModelManager.getConnection();

QueryRunner qRunner = new QueryRunner();

try {
return qRunner.update(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),SQLModelManager.getFieldArray(input));
} catch (SQLException e) {
e.printStackTrace();
} finally {
DbUtils.closeQuietly(conn);
}
return -1;
}
<#elseif sqlmodel.type == 'DELETE' >
@${attrs.apiType}
@Path("${attrs.subPath}")
public int get${attrs.apiName?cap_first}(${attrs.className?cap_first}Request input){
Connection conn = SQLModelManager.getConnection();

QueryRunner qRunner = new QueryRunner();

try {
return qRunner.update(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),SQLModelManager.getFieldArray(input));
} catch (SQLException e) {
e.printStackTrace();
} finally {
DbUtils.closeQuietly(conn);
}
return -1;
}
<#elseif sqlmodel.type == 'PROC' >
@${attrs.apiType}
@Path("${attrs.subPath}")
public <#if sqlmodel.outputParams?size ==0 >int<#else>get${attrs.apiName?cap_first}</#if> get${attrs.apiName?cap_first}(<#if sqlmodel.inputParams?size ==0 > <#else>${attrs.className?cap_first}Request input</#if>){
Connection conn = SQLModelManager.getConnection();

QueryRunner qRunner = new QueryRunner();

try {
    <#if sqlmodel.outputParams?size ==0 >
    return qRunner.update(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),SQLModelManager.getFieldArray(input));
    <#else>
    return qRunner.query(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),
    new BeanListHandler<>(${attrs.className?cap_first}Response.class)<#if sqlmodel.inputParams?size ==0 ><#else>, SQLModelManager.getFieldArray(input)</#if>);
    </#if>
} catch (SQLException e) {
    e.printStackTrace();
} finally {
    DbUtils.closeQuietly(conn);
}
    <#if sqlmodel.outputParams?size ==0 >
    return -1;
    <#else>
    return new ArrayList<${attrs.className?cap_first}Response>();
    </#if>
}
<#else>
@${attrs.apiType}
@Path("${attrs.subPath}")
public List<${attrs.className?cap_first}Response> get${attrs.apiName?cap_first}(<#if sqlmodel.inputParams?size ==0 > <#else>${attrs.className?cap_first}Request input</#if>){
    Connection conn = SQLModelManager.getConnection();

    QueryRunner qRunner = new QueryRunner();

    try {
    return qRunner.query(conn, SQLModelManager.getRegisterModel("/${attrs.path}/${attrs.subPath}").generateSQL(),
    new BeanListHandler<>(${attrs.className?cap_first}Response.class)<#if sqlmodel.inputParams?size ==0 ><#else>, SQLModelManager.getFieldArray(input)</#if>);
    } catch (SQLException e) {
    e.printStackTrace();
    } finally {
    DbUtils.closeQuietly(conn);
    }
    return new ArrayList<${attrs.className?cap_first}Response>();
    }
</#if>


    }

