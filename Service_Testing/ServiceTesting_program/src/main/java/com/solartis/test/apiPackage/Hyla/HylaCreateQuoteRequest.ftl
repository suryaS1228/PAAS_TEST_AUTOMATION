<#assign Numofdevices=[]><#list Noofdevices as x><#assign Numofdevices=Numofdevices+[x.value]></#list>
{    
	
      
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
          "deviceInformation": [
         <#assign i=1>
        <#list 1..Numofdevices[0] as result>
        <#assign deviceInformationDetails="deviceInformationDetail"+i>
            {
               <#list deviceInformationDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>  
           
         ],
<#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
    }