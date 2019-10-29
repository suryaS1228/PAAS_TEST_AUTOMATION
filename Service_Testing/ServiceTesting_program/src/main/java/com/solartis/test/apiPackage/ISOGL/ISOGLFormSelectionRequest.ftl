<#assign array=[]><#list NoOfState1 as x><#assign array=array+[x.value]></#list><#assign i=0>
 {
 <#list State as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
  "ServiceRequestDetail": {
   <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
  },
 
  "GeneralLiability": [
    {
      <#list GeneralLiability as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      "GeneralLiabilityLocation": [
        {
           <#list GeneralLiabilityLocation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
         
        "GeneralLiabilityClassification": [
            {
              
              <#list GeneralLiabilityClassification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
              "GeneralLiabilityMarylandChangesLiabilityForHazardsOfLeadClassLvl": [
                {
                  <#list GeneralLiabilityMarylandChangesLiabilityForHazardsOfLeadClassLvl as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
                }
              ]
              
             
            }
          ]
          
        }
      ]
    }
  ]
}