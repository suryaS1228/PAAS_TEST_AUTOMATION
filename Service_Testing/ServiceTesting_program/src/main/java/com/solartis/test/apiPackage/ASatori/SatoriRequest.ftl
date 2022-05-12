
{
  	<#list Policy as result>
  	"${result.atrib}":"${result.value}",
    </#list>
    
    "Account":
    {
    
    
     "Insured":
     {
     
     
     "InsuredAddress":
     {
     <#list InsuredAddress as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     
      }, 
      
       "Coverages":
     {
     <#list Coverages as result>
  	 "${result.atrib}":"${result.value}",
     </#list>
     
       "FiduciaryLiability":
     {
     <#list FiduciaryLiability as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     },
     
      "InvestmentAdviserProfessionalLiability":
     {
     <#list InvestmentAdviserProfessionalLiability as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     },
     
      "InvestmentFundLiability":
     {
     <#list InvestmentFundLiability as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     },
     
      "InvestmentAdviserManagementLiability":
     {
     <#list InvestmentAdviserManagementLiability as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     },
     
      "EmploymentPracticesLiability":
     {
     <#list EmploymentPracticesLiability as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
     }
     
     }
    
    }
    
    },
    
    "ServiceRequestDetail":
     {
     <#list ServiceRequestDetail as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
    }
 }
    