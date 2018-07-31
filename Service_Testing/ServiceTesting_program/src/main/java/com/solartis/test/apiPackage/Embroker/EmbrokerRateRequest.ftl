
<#assign DOCoverage=[]><#list DNOApplicable as y><#assign DOCoverage=DOCoverage+[y.value]></#list>
<#assign EPLCoverage=[]><#list EPLIApplicable as z><#assign EPLCoverage=EPLCoverage+[z.value]></#list>
<#assign FIDCoverage=[]><#list FidApplicable as a><#assign FIDCoverage=FIDCoverage+[a.value]></#list>
{   

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
             "CoverageDetail":[   
             
      <#if  DOCoverage[0]=="Yes">
      
         {
          <#list DNOCoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
        
       </#if>
       
        <#if  EPLCoverage[0]=="No">
      
         ,{
          <#list EPLICoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
         </#if>
        
         <#if  FIDCoverage[0]=="Yes">
         
         ,{
          <#list FidCoverage as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
        
         </#if>
         
         
           
        ]
        }
        ]
        }
        }          
        