<#assign numoftravelers=[]><#list Nooftravelers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
{    
	"EndClientUserUniqueSessionId" : "1",
     "EndClientUserBrowserSessionId" : "12",
     "EndClientUserBrowserInformation" : "192.168.5.12",
     "PaaSClientIPAddress" : "192.168.5.12",
     "ClientUniqueRequestID" : "12587",
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        },
        "PolicyInformation": {   
          <#list PolicyInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          "PaymentInformation": {
           <#list PaymentInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          },
             "TravelerList" : [        
          <#assign i=1>
        <#list 1..numoftravelers[0] as result>
        <#assign Travelers="Traveler"+i>
        {
         <#list Travelers?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
        ],
       }
       }   