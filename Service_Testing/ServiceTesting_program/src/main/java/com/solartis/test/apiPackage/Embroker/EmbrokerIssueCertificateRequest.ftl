<#assign NumofTFS=[]><#list NoofTFS as x><#assign NumofTFS=NumofTFS+[x.value]></#list>
<#assign DOCoverage=[]><#list DO as y><#assign DOCoverage=DOCoverage+[y.value]></#list>
<#assign EPLCoverage=[]><#list EPL as z><#assign EPLCoverage=EPLCoverage+[z.value]></#list>
<#assign FIDCoverage=[]><#list FID as a><#assign FIDCoverage=FIDCoverage+[a.value]></#list>
{   

	"EndClientUserUniqueSessionId": "Uniquesession",
	 "OwnerId": "36",
    "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "Policy":
         {   
          <#list Policy as result>"${result.atrib}":"${result.value}", </#list>
         
               "TFSList": [
               <#assign i=1>
        <#list 1..NumofTFS[0] as result>
        <#assign TFSDetails="TFSDetail"+i>
       
               {
               <#list TFSDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
               ],
                     
               "OutputTypeList": [
                {
                  "Type": "POLICY"
                 }
                ],
            "FormsList": [
            {
          <#list Formlist_Declaration as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        {
          <#list Formlist_GeneralTC as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }, 
      <#if  DOCoverage[0]=="Yes">
         {
          <#list Formlist_DO as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
       </#if>
       
        <#if  EPLCoverage[0]=="Yes">
         {
          <#list Formlist_EPL as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
         </#if>
        
         <#if  FIDCoverage[0]=="Yes">
         {
          <#list Formlist_FID as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        
         </#if> 
         {
          <#list Formlist_Definition as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
         {
          <#list Formlist_State_Disclosure as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
           
        ]
        }
        
        }          
        