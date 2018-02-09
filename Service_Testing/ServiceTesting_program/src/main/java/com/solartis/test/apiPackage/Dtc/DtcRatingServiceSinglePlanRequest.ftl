<#assign TravelerDOBarray=[]><#list TravelerDOB as x><#assign TravelerDOBarray=TravelerDOBarray+[x.value]></#list>
<#assign TravelCostarray=[]><#list TravelCost as x><#assign TravelCostarray=TravelCostarray+[x.value]></#list>
<#assign TravelIndexarray=[]><#list TravelerIndex as x><#assign TravelIndexarray=TravelIndexarray+[x.value]></#list>
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
        "QuoteInformation": {
        "TravelerList" : [        
          <#assign i=0>
        <#list 1..numoftravelers[0] as result>
        {
         "TravelerDOB":"${TravelerDOBarray[i]}",
         "TravelCost" : "${TravelCostarray[i]}",
          "TravelerIndex":"${TravelIndexarray[i]}"
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
        ],
        <#list QuoteInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
      }
    }