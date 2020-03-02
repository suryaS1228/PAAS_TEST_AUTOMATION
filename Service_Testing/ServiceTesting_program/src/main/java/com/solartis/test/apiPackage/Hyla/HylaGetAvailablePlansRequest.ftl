<#assign Numoftier=[]><#list NoofTier as x><#assign Numoftier=Numoftier+[x.value]></#list>
{    
	
      
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "tierDetail":[
         <#assign i=1>
        <#list 1..Numoftier[0] as result>
        <#assign tierDetails="tierDetail"+i>
       
               {
               <#list tierDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
      ],   
      <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
    }