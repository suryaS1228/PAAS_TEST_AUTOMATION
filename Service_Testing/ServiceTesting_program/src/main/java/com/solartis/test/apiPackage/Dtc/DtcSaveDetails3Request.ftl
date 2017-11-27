<#assign TravelerDOBarray=[]><#list TravelerDOB as x><#assign TravelerDOBarray=TravelerDOBarray+[x.value]></#list>
<#assign TravelCostarray=[]><#list TravelCost as x><#assign TravelCostarray=TravelCostarray+[x.value]></#list>
<#assign TravelIndexarray=[]><#list TravelerIndex as x><#assign TravelIndexarray=TravelIndexarray+[x.value]></#list>
{
  "ServiceRequestDetail": 
  {
    <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
  "CustomerInformation": 
   {
     "TravelerList" : [
     <#list 1..numoftravelers[0] as result>
     {
       "TravelerDOB":"${TravelerDOBarray[i]}",
       "TravelCost" : "${TravelCostarray[i]}",
       "TravelerIndex":"${TravelIndexarray[i]}"
     }<#if result?is_last><#else>,</#if><#assign i=i+1>
     </#list>
     <#list CustomerInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
   }
}