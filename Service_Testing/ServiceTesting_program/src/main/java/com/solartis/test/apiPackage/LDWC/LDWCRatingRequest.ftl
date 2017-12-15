<#assign StateProvCdarray=[]><#list StateProvCd as x><#assign StateProvCdarray=StateProvCdarray+[x.value]></#list>
<#assign FormatModFactorarray=[]><#list FormatModFactor as x><#assign FormatModFactorarray=FormatModFactorarray+[x.value]></#list>
<#assign ALAEarray=[]><#list ALAE as x><#assign ALAEarray=ALAEarray+[x.value]></#list>
<#assign LDCCarray=[]><#list LDCC as x><#assign LDCCarray=LDCCarray+[x.value]></#list>
<#assign ARAParray=[]><#list ARAP as x><#assign ARAParray=ARAParray+[x.value]></#list>
<#assign Exposurearray=[]><#list Exposure as y><#assign Exposurearray=Exposurearray+[y.value]></#list>
<#assign RatingClassificationCdarray=[]><#list RatingClassificationCd as y><#assign RatingClassificationCdarray=RatingClassificationCdarray+[y.value]></#list>
<#assign GoverningClassIndarray=[]><#list GoverningClassInd as y><#assign GoverningClassIndarray=GoverningClassIndarray+[y.value]></#list>
<#assign numofStatearray=[]><#list numofState as x><#assign numofStatearray=numofStatearray+[x.value]></#list>
<#assign i=0>
<#assign numofClassarray=[]><#list numofClass as y><#assign numofClassarray=numofClassarray+[y.value]></#list>
<#assign j=0>
<ACORD>
	<SignonRq>
		<SignonPswd>
			<CustId>
				<CustLoginId>starrcscadmin</CustLoginId>
			</CustId>
			<CustPswd>
				<Pswd>sKVSbX7wj8UYYAaar4amxQ==</Pswd>
			</CustPswd>
		</SignonPswd>
		<ClientDt>2012-09-14T11:03:46.073</ClientDt>
		<ClientApp>
			<Org>Starr</Org>
			<Name>LDCC</Name>
			<Version>1.00</Version>
		</ClientApp>
	</SignonRq>
	<InsuranceSvcRq>
	<#list Insurance as result><${result.atrib}>${result.value}</${result.atrib}>
        </#list>
        <LargeDeductibleCreditCalculationRq>
	   <MsgStatus>
	      <#list MsgStatus as result><${result.atrib}>${result.value}</${result.atrib}>
              </#list>
	      </MsgStatus>
	<#list LargeDeductibleCredit as result><${result.atrib}>${result.value}</${result.atrib}>
        </#list>
    	<Producer>
	   <ItemIdInfo>
	      <AgencyId>1800001</AgencyId>
	   </ItemIdInfo>
	   <ProducerInfo>
	      <ContractNumber>starrunderwriter</ContractNumber>
	   </ProducerInfo>
	</Producer>
	<InsuredOrPrincipal>
	     <GeneralPartyInfo>
	        <NameInfo>
	           <CommlName>
		      <#list GeneralPartyInfo as result><${result.atrib}>${result.value}</${result.atrib}>
                      </#list>
                      <com.csc_ClientNumber/> 
    	           </CommlName>
		</NameInfo>
	     </GeneralPartyInfo>
	  <InsuredOrPrincipalInfo>
	    <InsuredOrPrincipalRoleCd>NI</InsuredOrPrincipalRoleCd>
	  </InsuredOrPrincipalInfo>
	</InsuredOrPrincipal>
	<CommlPolicy>
	  <#list CommlPolicy as result><${result.atrib}>${result.value}</${result.atrib}>
          </#list>
    	  <ContractTerm>
	     <#list ContractTerm as result><${result.atrib}>${result.value}</${result.atrib}>
             </#list>
    	  </ContractTerm>
    	  <PolicyStatusCd>N</PolicyStatusCd>
    	  <CurrentTermAmt>
	     <#list CurrentTermAmt as result><${result.atrib}>${result.value}</${result.atrib}>
             </#list>
	  </CurrentTermAmt>
          <#list TxnTypesub as result><${result.atrib}>${result.value}</${result.atrib}>
          </#list>
	  <CommlCoverage>
	     <CoverageCd>WC</CoverageCd>
		<Deductible>
		   <DeductibleTypeCd>00</DeductibleTypeCd>
		   <DeductibleAppliesToCd>Amount</DeductibleAppliesToCd>
		   <FormatCurrency>
		      <#list FormatCurrency1 as result><${result.atrib}>${result.value}</${result.atrib}>
                      </#list>
    		   </FormatCurrency>
		</Deductible>
		<Deductible>
		   <DeductibleTypeCd>00</DeductibleTypeCd>
		   <DeductibleAppliesToCd>Aggregate</DeductibleAppliesToCd>
		   <FormatCurrency>
			<#list FormatCurrency2 as result><${result.atrib}>${result.value}</${result.atrib}>
                        </#list>
    	           </FormatCurrency>
		</Deductible>
	    </CommlCoverage>
	 </CommlPolicy>
                  <WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<#list 1..numofStatearray[0] as result>
				<WorkCompRateState>
					<StateProvCd>${StateProvCdarray[i]}</StateProvCd>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<FormatModFactor>${FormatModFactorarray[i]}</FormatModFactor> 
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							 <CreditSurchargeCd>${ALAEarray[i]}</CreditSurchargeCd>
					<NumericValue>
							<FormatModFactor>${LDCCarray[i]}</FormatModFactor>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<FormatModFactor>${ARAParray[i]}</FormatModFactor> 
						</NumericValue>
					</CreditOrSurcharge>
                                   <#list 1..numofClassarray[0] as result>
					<WorkCompRateClass> 
                                                <Exposure> <#if Exposurearray[j]?has_content>${Exposurearray[j]}<#else></#if> </Exposure> 
                                                <RatingClassificationCd><#if RatingClassificationCdarray[j]?has_content>${RatingClassificationCdarray[j]}<#else></#if></RatingClassificationCd>
                                                <com.csc_GoverningClassInd><#if GoverningClassIndarray[j]?has_content>${GoverningClassIndarray[j]}<#else></#if></com.csc_GoverningClassInd>
                             
          </WorkCompRateClass> 
                                   <#assign j=j+1>
         </#list>
				</WorkCompRateState>
				<#assign i=i+1>
         </#list>
			</WorkCompLineBusiness>
			 </LargeDeductibleCreditCalculationRq> 
  
                    </InsuranceSvcRq> 

                   </ACORD>
			