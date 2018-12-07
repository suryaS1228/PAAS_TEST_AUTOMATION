<#assign array=[]><#list NoOfJewels as x><#assign array=array+[x.value]></#list><#assign i=0>
{
  "Policy": {
  <#list "Policy"?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "InsuredInformation": {
  <#list "InsuredInformation"?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "JewelerInformation": {
  <#list "JewelerInformation"?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "JewelList": [
  	<#list 1..array[i] as x>
    {
      "JewelDetail": {<#assign JewelDetails="JewelDetail"+x>
      <#list JewelDetails?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
      }
    }<#if x?is_last><#else>,</#if>
	</#list>
  ],
  "OwnerId": "41",
  "ServiceRequestDetail": {
  <#list "ServiceRequestDetail"?eval  as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	          </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}