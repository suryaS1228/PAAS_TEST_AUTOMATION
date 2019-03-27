<#assign Numofcoverage=[]><#list Noofcoverage as x><#assign Numofcoverage=Numofcoverage+[x.value]></#list>
{   

	"EndClientUserUniqueSessionId": "Uniquesession",
	 "OwnerId": "36",
    "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "Policy":
         {   
          <#list Policy as result>"${result.atrib}":"${result.value}",</#list>
       <#--    "Coverage": [
                
                     <#assign i=1>
        <#list 1..Numofcoverage[0] as result>
        <#assign CoverageDetail="CoverageDetail"+i>
        {
                
                "CoverageDetail": {
        
         <#list CoverageDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }}<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
               
               
               ]-->
              
               }
               
               }