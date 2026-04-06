const defaultStates = [
    'Andhra Pradesh', 'Assam', 'Bihar', 'Chhattisgarh', 'Delhi', 'Gujarat', 
    'Haryana', 'Jharkhand', 'Karnataka', 'Kerala', 'Madhya Pradesh', 
    'Maharashtra', 'Odisha', 'Punjab', 'Rajasthan', 'Tamil Nadu', 
    'Telangana', 'Uttar Pradesh', 'West Bengal'
];

const stateDistricts = {
    'Delhi': ['New Delhi', 'South Delhi', 'East Delhi', 'West Delhi', 'North Delhi', 'Central Delhi'],
    'Maharashtra': ['Mumbai', 'Pune', 'Nagpur', 'Thane', 'Nashik', 'Aurangabad'],
    'Karnataka': ['Bangalore', 'Mysore', 'Mangalore', 'Hubli', 'Belgaum', 'Gulbarga'],
    'Tamil Nadu': ['Chennai', 'Coimbatore', 'Madurai', 'Trichy', 'Salem', 'Tirunelveli'],
    'Gujarat': ['Ahmedabad', 'Surat', 'Vadodara', 'Rajkot', 'Bhavnagar', 'Jamnagar'],
    'Rajasthan': ['Jaipur', 'Jodhpur', 'Udaipur', 'Kota', 'Ajmer', 'Bikaner'],
    'West Bengal': ['Kolkata', 'Howrah', 'Asansol', 'Siliguri', 'Durgapur', 'Berhampore'],
    'Kerala': ['Thiruvananthapuram', 'Kochi', 'Kozhikode', 'Thrissur', 'Kollam', 'Palakkad'],
    'Uttar Pradesh': ['Lucknow', 'Varanasi', 'Kanpur', 'Agra', 'Allahabad', 'Meerut'],
    'Madhya Pradesh': ['Bhopal', 'Indore', 'Gwalior', 'Jabalpur', 'Ujjain', 'Sagar'],
    'Telangana': ['Hyderabad', 'Warangal', 'Nizamabad', 'Karimnagar', 'Ramagundam', 'Secunderabad'],
    'Punjab': ['Chandigarh', 'Ludhiana', 'Amritsar', 'Jalandhar', 'Patiala', 'Bathinda'],
    'Haryana': ['Gurgaon', 'Faridabad', 'Panipat', 'Ambala', 'Karnal', 'Rohtak'],
    'Bihar': ['Patna', 'Gaya', 'Muzaffarpur', 'Bhagalpur', 'Darbhanga', 'Purnia'],
    'Odisha': ['Bhubaneswar', 'Cuttack', 'Rourkela', 'Berhampur', 'Sambalpur', 'Balasore'],
    'Chhattisgarh': ['Raipur', 'Bhilai', 'Durg', 'Bilaspur', 'Korba', 'Rajnandgaon'],
    'Jharkhand': ['Ranchi', 'Jamshedpur', 'Dhanbad', 'Bokaro', 'Hazaribagh', 'Deoghar'],
    'Assam': ['Guwahati', 'Silchar', 'Dibrugarh', 'Jorhat', 'Tezpur', 'Bongaigaon'],
    'Andhra Pradesh': ['Visakhapatnam', 'Vijayawada', 'Guntur', 'Tirupati', 'Nellore', 'Kurnool']
};

document.addEventListener('DOMContentLoaded', function() {
    loadStates();
});

function loadStates() {
    const stateSelect = document.getElementById('state');
    if (!stateSelect) return;
    
    stateSelect.innerHTML = '<option value="">Select State</option>';
    
    defaultStates.forEach(state => {
        const option = document.createElement('option');
        option.value = state;
        option.textContent = state;
        stateSelect.appendChild(option);
    });
}

function loadDistricts() {
    const state = document.getElementById('state').value;
    const districtSelect = document.getElementById('district');
    
    if (!districtSelect) return;
    
    districtSelect.innerHTML = '<option value="">Loading...</option>';
    
    if (!state) {
        districtSelect.innerHTML = '<option value="">Select State First</option>';
        return;
    }

    const districts = stateDistricts[state] || [state];
    
    districtSelect.innerHTML = '<option value="">Select District</option>';
    districts.forEach(district => {
        const option = document.createElement('option');
        option.value = district;
        option.textContent = district;
        districtSelect.appendChild(option);
    });
}

function nextStep(step) {
    document.querySelectorAll('.form-step').forEach(el => el.classList.remove('active'));
    document.getElementById('step' + step).classList.add('active');
    
    document.querySelectorAll('.progress-step').forEach(el => {
        const stepNum = parseInt(el.getAttribute('data-step'));
        if (stepNum < step) {
            el.classList.add('completed');
            el.classList.remove('active');
        } else if (stepNum === step) {
            el.classList.add('active');
            el.classList.remove('completed');
        } else {
            el.classList.remove('active', 'completed');
        }
    });
}

function prevStep(step) {
    document.querySelectorAll('.form-step').forEach(el => el.classList.remove('active'));
    document.getElementById('step' + step).classList.add('active');
    
    document.querySelectorAll('.progress-step').forEach(el => {
        const stepNum = parseInt(el.getAttribute('data-step'));
        if (stepNum < step) {
            el.classList.add('completed');
            el.classList.remove('active');
        } else if (stepNum === step) {
            el.classList.add('active');
            el.classList.remove('completed');
        } else {
            el.classList.remove('active', 'completed');
        }
    });
}

function validateCurrentStep() {
    const activeStep = document.querySelector('.form-step.active');
    if (!activeStep) return false;
    
    const inputs = activeStep.querySelectorAll('input[required], select[required]');
    let valid = true;

    inputs.forEach(input => {
        if (input.type === 'radio') {
            const checked = document.querySelector('input[name="' + input.name + '"]:checked');
            if (!checked) {
                valid = false;
            }
        } else if (!input.value) {
            input.style.borderColor = '#e74c3c';
            valid = false;
            setTimeout(() => input.style.borderColor = '', 2000);
        } else {
            input.style.borderColor = '';
        }
    });
    
    return valid;
}

function scrollToForm() {
    const form = document.getElementById('assessment-form');
    if (form) {
        form.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}

function submitForm() {
    if (!validateCurrentStep()) {
        return;
    }

    const state = document.getElementById('state').value;
    const districtEl = document.getElementById('district');
    const district = districtEl ? (districtEl.value || state) : state;
    const locationTypeEl = document.querySelector('input[name="locationType"]:checked');
    const locationType = locationTypeEl ? locationTypeEl.value : 'urban';
    const numberOfDwellers = parseInt(document.getElementById('numberOfDwellers').value) || 4;
    const roofArea = parseFloat(document.getElementById('roofArea').value) || 100;
    const roofTypeEl = document.getElementById('roofType');
    const roofType = roofTypeEl ? roofTypeEl.value : 'concrete';
    const openSpace = parseFloat(document.getElementById('openSpace').value) || 20;
    const soilTypeEl = document.getElementById('soilType');
    const soilType = soilTypeEl ? soilTypeEl.value : 'alluvial';
    const areaTypeEl = document.querySelector('input[name="areaType"]:checked');
    const areaType = areaTypeEl ? areaTypeEl.value : 'town';

    const data = {
        name: 'User',
        state: state,
        district: district,
        locationType: locationType,
        areaType: areaType,
        numberOfDwellers: numberOfDwellers,
        roofArea: roofArea,
        roofType: roofType,
        openSpace: openSpace,
        soilType: soilType,
        costYear: document.getElementById('costYear')?.value || 2026,
        materialQuality: document.querySelector('input[name="materialQuality"]:checked')?.value || 'standard',
        language: 'en'
    };

    console.log('Submitting assessment data:', JSON.stringify(data));

    const loadingOverlay = document.getElementById('loading-overlay');
    if (loadingOverlay) {
        loadingOverlay.classList.remove('hidden');
    }

    fetch('/assess', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        console.log('Response status:', response.status);
        if (!response.ok) {
            return response.text().then(text => {
                console.error('Error response:', text);
                throw new Error('Server error: ' + response.status);
            });
        }
        return response.json();
    })
    .then(result => {
        console.log('Assessment result:', result);
        
        lastAssessmentResult = result;
        
        if (loadingOverlay) {
            loadingOverlay.classList.add('hidden');
        }
        
        if (result.error) {
            alert('Error: ' + result.error);
            return;
        }
        
        displayResults(result);
    })
    .catch(error => {
        console.error('Error:', error);
        
        if (loadingOverlay) {
            loadingOverlay.classList.add('hidden');
        }
        
        alert('Error: ' + error.message + '\n\nPlease try again. Check console for details.');
    });
}

function displayResults(result) {
    const formSection = document.getElementById('assessment-form');
    const resultsSection = document.getElementById('results-section');
    
    if (formSection) formSection.style.display = 'none';
    if (resultsSection) resultsSection.classList.remove('hidden');

    document.querySelectorAll('.progress-step').forEach(el => {
        el.classList.add('completed');
        el.classList.remove('active');
    });
    const step4 = document.querySelector('[data-step="4"]');
    if (step4) step4.classList.add('active');

    setResultValue('result-rainfall', result.annualRainfall);
    setResultValue('result-demand', result.waterDemand, 2);
    setResultValue('result-runoff', result.runoffGeneration, 2);
    setResultValue('result-surplus', result.surplusWater, 2);
    
    const co2Value = result.environmentalImpact || 0;
    setResultValue('result-co2', co2Value, 2);

    const badge = document.getElementById('feasibility-badge');
    if (badge) {
        if (result.feasible) {
            badge.textContent = 'Feasible for Rainwater Harvesting';
            badge.className = 'badge success';
        } else {
            badge.textContent = 'Moderate Potential';
            badge.className = 'badge warning';
        }
    }

    setTextContent('aquifer-type', result.aquiferType || 'Unknown');
    setTextContent('groundwater-level', (result.groundwaterLevel || 0) + ' m below ground level');
    setTextContent('structure-name', result.recommendedStructure || 'N/A');
    setTextContent('structure-desc', result.structureDescription || 'N/A');

    if (result.dimensions) {
        setTextContent('dim-type', result.dimensions.structureType || 'N/A');
        setTextContent('dim-length', result.dimensions.length ? result.dimensions.length.toFixed(2) + ' m' : 'N/A');
        setTextContent('dim-width', result.dimensions.width ? result.dimensions.width.toFixed(2) + ' m' : 'N/A');
        setTextContent('dim-depth', result.dimensions.depth ? result.dimensions.depth.toFixed(2) + ' m' : 'N/A');
        setTextContent('dim-capacity', (result.dimensions.tankCapacity || 0) + ' KL');
    }

    if (result.costEstimation) {
        const totalCost = result.costEstimation.totalCost || 0;
        const minCost = Math.round(totalCost * 0.8);
        const maxCost = Math.round(totalCost * 1.2);
        
        function formatRange(value) {
            const min = Math.round(value * 0.8);
            const max = Math.round(value * 1.2);
            return '₹ ' + min.toLocaleString('en-IN') + ' - ₹ ' + max.toLocaleString('en-IN');
        }
        
        setCurrencyText('cost-structure', result.costEstimation.structureCost);
        setCurrencyText('cost-plumbing', result.costEstimation.plumbingCost);
        setCurrencyText('cost-filtration', result.costEstimation.filtrationCost);
        
        document.getElementById('cost-total').innerHTML = 
            '₹ ' + minCost.toLocaleString('en-IN') + ' - ₹ ' + maxCost.toLocaleString('en-IN');
        
        setTextContent('payback-period', (result.costEstimation.paybackPeriod || 0) + ' years');
        setCurrencyText('savings-20yr', result.costEstimation.savingsOver20Years);
        setTextContent('co2-reduction', (result.environmentalImpact || 0) + ' kg/year');
    }

    const feasibilityMsg = document.getElementById('feasibility-message');
    if (feasibilityMsg) {
        feasibilityMsg.innerHTML = result.feasibilityMessage || 'Assessment completed.';
    }

    if (resultsSection) {
        resultsSection.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }

    animateValues();
}

function setResultValue(elementId, value, decimals = 0) {
    const el = document.getElementById(elementId);
    if (el) {
        if (value !== undefined && value !== null) {
            el.textContent = decimals > 0 ? value.toFixed(decimals) : value;
        } else {
            el.textContent = '0';
        }
    }
}

function setTextContent(elementId, text) {
    const el = document.getElementById(elementId);
    if (el) {
        el.textContent = text || 'N/A';
    }
}

function setCurrencyText(elementId, amount) {
    const el = document.getElementById(elementId);
    if (el) {
        el.textContent = '₹ ' + (amount || 0).toLocaleString('en-IN');
    }
}

function formatCurrency(amount) {
    return '₹ ' + (amount || 0).toLocaleString('en-IN');
}

function animateValues() {
    const values = document.querySelectorAll('.result-value');
    values.forEach(value => {
        const text = value.textContent;
        const finalValue = parseFloat(text);
        if (!isNaN(finalValue) && finalValue > 0) {
            let current = 0;
            const increment = finalValue / 30;
            const timer = setInterval(() => {
                current += increment;
                if (current >= finalValue) {
                    value.textContent = finalValue % 1 === 0 ? finalValue : finalValue.toFixed(2);
                    clearInterval(timer);
                } else {
                    value.textContent = current % 1 === 0 ? current : current.toFixed(2);
                }
            }, 30);
        }
    });
}

function resetForm() {
    const form = document.getElementById('harvestForm');
    const resultsSection = document.getElementById('results-section');
    const formSection = document.getElementById('assessment-form');
    
    if (form) form.reset();
    if (resultsSection) resultsSection.classList.add('hidden');
    if (formSection) formSection.style.display = 'block';
    
    document.querySelectorAll('.form-step').forEach(el => el.classList.remove('active'));
    document.getElementById('step1').classList.add('active');
    
    document.querySelectorAll('.progress-step').forEach(el => {
        el.classList.remove('active', 'completed');
    });
    document.querySelector('[data-step="1"]').classList.add('active');

    if (formSection) {
        formSection.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}

let lastAssessmentResult = null;

function generatePdf() {
    if (!lastAssessmentResult) {
        alert('No assessment data available');
        return;
    }

    const inputData = {
        name: 'User',
        state: document.getElementById('state').value,
        district: document.getElementById('district').value,
        locationType: document.querySelector('input[name="locationType"]:checked')?.value || 'urban',
        areaType: document.querySelector('input[name="areaType"]:checked')?.value || 'town',
        numberOfDwellers: parseInt(document.getElementById('numberOfDwellers').value) || 4,
        roofArea: parseFloat(document.getElementById('roofArea').value) || 100,
        roofType: document.getElementById('roofType').value,
        openSpace: parseFloat(document.getElementById('openSpace').value) || 20,
        soilType: document.getElementById('soilType').value,
        isFeasible: lastAssessmentResult.isFeasible,
        recommendedStructure: lastAssessmentResult.recommendedStructure,
        runoffGenerated: lastAssessmentResult.runoffGenerated,
        waterDemand: lastAssessmentResult.waterDemand,
        surplusWater: lastAssessmentResult.surplusWater,
        annualRainfall: lastAssessmentResult.annualRainfall,
        message: lastAssessmentResult.message,
        dimType: lastAssessmentResult.dimensions?.type || '',
        dimLength: lastAssessmentResult.dimensions?.length || 0,
        dimWidth: lastAssessmentResult.dimensions?.width || 0,
        dimDepth: lastAssessmentResult.dimensions?.depth || 0,
        dimCapacity: lastAssessmentResult.dimensions?.capacity || 0,
        structureCost: lastAssessmentResult.costEstimation?.structureCost || 0,
        plumbingCost: lastAssessmentResult.costEstimation?.plumbingCost || 0,
        filtrationCost: lastAssessmentResult.costEstimation?.filtrationCost || 0,
        totalCost: lastAssessmentResult.costEstimation?.totalCost || 0,
        paybackPeriod: lastAssessmentResult.costEstimation?.paybackPeriod || 0,
        waterSaved20Years: lastAssessmentResult.environmentalImpact?.waterSaved20Years || 0,
        co2Reduction: lastAssessmentResult.environmentalImpact?.co2Reduction || 0,
        costSavings20Years: lastAssessmentResult.environmentalImpact?.costSavings20Years || 0,
        aquiferType: lastAssessmentResult.aquiferType || '',
        groundwaterLevel: lastAssessmentResult.groundwaterLevel || 0,
        runoffCoefficient: lastAssessmentResult.aquiferData?.runoffCoefficient || 0.3,
        aquiferSoilType: lastAssessmentResult.aquiferData?.soilType || ''
    };

    fetch('/api/generate-pdf', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(inputData)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => {
                console.error('Server error:', text);
                throw new Error('PDF generation failed: ' + text);
            });
        }
        return response.blob();
    })
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'JalDhara_Assessment_Report.pdf';
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
    })
    .catch(err => {
        console.error('Error generating PDF:', err);
        alert('Error generating PDF. Please try again.');
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const roofAreaInput = document.getElementById('roofArea');
    const rangeSlider = document.querySelector('.range-slider input[type="range"]');
    
    if (roofAreaInput && rangeSlider) {
        roofAreaInput.addEventListener('input', function() {
            rangeSlider.value = this.value;
        });
        
        rangeSlider.addEventListener('input', function() {
            roofAreaInput.value = this.value;
        });
    }
});

function toggleCustomCost() {
    const checkbox = document.getElementById('customCostToggle');
    const customFields = document.getElementById('customCostFields');
    const costTotal = document.getElementById('cost-total');
    
    if (checkbox && customFields) {
        if (checkbox.checked) {
            customFields.classList.remove('hidden');
            if (lastAssessmentResult && lastAssessmentResult.costEstimation) {
                const structCost = lastAssessmentResult.costEstimation.structureCost || 0;
                const structShare = structCost * 0.2;
                document.getElementById('customExcavation').value = Math.round(structShare);
                document.getElementById('customPCC').value = Math.round(structShare * 1.6);
                document.getElementById('customStoneGravel').value = Math.round(structShare * 2);
                document.getElementById('customBrickwork').value = Math.round(structShare * 1.5);
                document.getElementById('customLabour').value = Math.round(structShare * 2);
                document.getElementById('customPlumbingCost').value = lastAssessmentResult.costEstimation.plumbingCost || 0;
                document.getElementById('customFiltrationCost').value = lastAssessmentResult.costEstimation.filtrationCost || 0;
                calculateCustomTotal();
            }
        } else {
            customFields.classList.add('hidden');
            if (lastAssessmentResult && lastAssessmentResult.costEstimation) {
                const totalCost = lastAssessmentResult.costEstimation.totalCost || 0;
                const minCost = Math.round(totalCost * 0.8);
                const maxCost = Math.round(totalCost * 1.2);
                costTotal.innerHTML = '₹ ' + minCost.toLocaleString('en-IN') + ' - ₹ ' + maxCost.toLocaleString('en-IN');
            }
        }
    }
}

function calculateCustomTotal() {
    const excavation = parseFloat(document.getElementById('customExcavation').value) || 0;
    const pcc = parseFloat(document.getElementById('customPCC').value) || 0;
    const stoneGravel = parseFloat(document.getElementById('customStoneGravel').value) || 0;
    const brickwork = parseFloat(document.getElementById('customBrickwork').value) || 0;
    const labour = parseFloat(document.getElementById('customLabour').value) || 0;
    
    const structureCost = excavation + pcc + stoneGravel + brickwork + labour;
    document.getElementById('customStructureCost').value = structureCost;
    
    const plumbingCost = parseFloat(document.getElementById('customPlumbingCost').value) || 0;
    const filtrationCost = parseFloat(document.getElementById('customFiltrationCost').value) || 0;
    
    const total = structureCost + plumbingCost + filtrationCost;
    document.getElementById('customTotal').innerHTML = '₹ ' + total.toLocaleString('en-IN');
    
    const costTotal = document.getElementById('cost-total');
    if (costTotal) {
        const minCost = Math.round(total * 0.8);
        const maxCost = Math.round(total * 1.2);
        costTotal.innerHTML = '₹ ' + minCost.toLocaleString('en-IN') + ' - ₹ ' + maxCost.toLocaleString('en-IN');
    }
}