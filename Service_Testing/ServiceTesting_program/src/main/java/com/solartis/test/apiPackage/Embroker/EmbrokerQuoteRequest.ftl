<#assign DOCoverage=[]><#list DNOApplicable as y><#assign DOCoverage=DOCoverage+[y.value]></#list>
<#assign EPLCoverage=[]><#list EPLIApplicable as z><#assign EPLCoverage=EPLCoverage+[z.value]></#list>
<#assign FIDCoverage=[]><#list FidApplicable as a><#assign FIDCoverage=FIDCoverage+[a.value]></#list>
<#assign EOCoverage=[]><#list ENOAllpicable as b><#assign EOCoverage=EOCoverage+[b.value]></#list>
{   
     <#list ClientUniqueRequestID as result>"${result.atrib}":"${result.value}", </#list>
	"EndClientUserUniqueSessionId": "Uniquesession",
	 "OwnerId": "36",
    "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "Policy":
         {   
          <#list Policy as result>"${result.atrib}":"${result.value}", </#list>
         "Coverage": [
      {
      
       <#if  DOCoverage[0]=="Yes">
        "CoverageDetail": 
        {
       
        <#list DNOCoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
           
         }
      }  </#if>
      <#if  EPLCoverage[0]=="Yes">
      ,{
        "CoverageDetail": 
       {
       
       <#list EPLICoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
       
       }
      }
      </#if>
       <#if  FIDCoverage[0]=="Yes">,
      {
        "CoverageDetail": 
      {
     
      <#list FidCoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
     
      }
      }
       </#if>,
       <#if  EOCoverage[0]=="Yes">,
      {
        "CoverageDetail": 
      {
     
      <#list ENOCoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
     
      }
      }
       </#if>
    ]
  }
}