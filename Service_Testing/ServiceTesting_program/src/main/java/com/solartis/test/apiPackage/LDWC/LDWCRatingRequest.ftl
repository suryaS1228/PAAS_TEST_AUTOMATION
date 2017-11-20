<#ftl output_format="XML" auto_esc=true>
<#macro EMOD atrib value> <${atrib}>${value}<${result.atrib}> </#macro>
<#macro LD atrib value> <${atrib}>${value}<${result.atrib}> </#macro>
<#macro LDValue atrib value> <${atrib}>${value}<${result.atrib}> </#macro>
<#macro MRAp atrib value> <${atrib}>${value}<${result.atrib}> </#macro>
<#macro EMOD5 atrib value> <${atrib}>${value}<${result.atrib}> </#macro>
<#assign emodatrib=[]><#assign emodvalue=[]><#list EMOD1 as result><#assign emodatrib=emodatrib+result.atrib><#assign emodvalue=emodvalue+result.value></#list>
<#assign LDatrib=[]><#assign LDvalue=[]><#list EMOD2 as result><#assign LDatrib=LDatrib+result.atrib><#assign LDvalue=LDvalue+result.value></#list>
<#assign LDValueatrib=[]><#assign LDValuevalue=[]><#list EMOD3 as result><#assign LDValueatrib=LDValueatrib+result.atrib><#assign LDValuevalue=LDValuevalue+result.value></#list>
<#assign MRApatrib=[]><#assign MRApvalue=[]><#list EMOD4 as result><#assign MRApatrib=MRApatrib+result.atrib><#assign MRApvalue=MRApvalue+result.value></#list>
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
	<#list Insurance as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    <LargeDeductibleCreditCalculationRq>
		<MsgStatus>
	<#list MsgStatus as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
		</MsgStatus>
	<#list LargeDeductibleCredit as result><${result.atrib}>${result.value}<${result.atrib}>
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
						<#list GeneralPartyInfo as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    					</CommlName>
					</NameInfo>
				</GeneralPartyInfo>
				<InsuredOrPrincipalInfo>
					<InsuredOrPrincipalRoleCd>NI</InsuredOrPrincipalRoleCd>
				</InsuredOrPrincipalInfo>
			</InsuredOrPrincipal>
			<CommlPolicy>
			<#list CommlPolicy as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    		<ContractTerm>
			<#list ContractTerm as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    		</ContractTerm>
    		<CurrentTermAmt>
			<#list CurrentTermAmt as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
				</CurrentTermAmt>
			<CommlCoverage>
					<CoverageCd>WC</CoverageCd>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Amount</DeductibleAppliesToCd>
						<FormatCurrency>
						<#list FormatCurrency as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    					</FormatCurrency>
					</Deductible>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Aggregate</DeductibleAppliesToCd>
						<FormatCurrency>
						<#list FormatCurrency1 as result><${result.atrib}>${result.value}<${result.atrib}>
    </#list>
    					</FormatCurrency>
					</Deductible>
				</CommlCoverage>
			</CommlPolicy>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd><#assign i=0>
				<#list State as result>
				<WorkCompRateState>
					<${result.atrib}>${result.value}<${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<@EMOD attrib=emodatrib[i] value=emodvalue[i]/>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<@LD attrib=LDatrib[i] value=LDvalue[i]/>
					<NumericValue>
							<@LDValue attrib=LDValueatrib[i] value=LDValuevalue[i]/>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<@MRAp attrib=MRApatrib[i] value=MRApvalue[i]/>
						</NumericValue>
					</CreditOrSurcharge>
					<#list classification as result>
					<WorkCompRateClass>
							<#list WorkCompRateClass as result><${result.atrib}>${result.value}<${result.atrib}></#list>
					</WorkCompRateClass>
					</#list>
				</WorkCompRateState><#assign i=i+1>
    </#list>
			</WorkCompLineBusiness>
		</LargeDeductibleCreditCalculationRq>
	</InsuranceSvcRq>
</ACORD>