package ${attrs.packageName};

public class ${attrs.className?cap_first}Response {
<#list bean as a>
private ${a.fieldClassName} ${a.fieldName};
</#list>

<#list bean as a>
public void set${a.fieldName?cap_first}(${a.fieldClassName} ${a.fieldName}){
this.${a.fieldName} = ${a.fieldName};
}

public ${a.fieldClassName} get${a.fieldName?cap_first}(){
return this.${a.fieldName};
}

</#list>
}