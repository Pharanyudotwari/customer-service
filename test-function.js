
const countOfWords = async (regex = /flight/g) => {
    const txt = 
    'The head of Singapore Airlines has apologised after a flight on Tuesday was hit by &quot;severe' +
    'extreme turbulence&quot;, killing one person and injuring dozens.' +
    'Goh Choon Phong said the airline was &quot;very sorry for the traumatic experience&quot; for those' +
    'onboard SQ321 from London to Singapore.' +
    'The was forced to make an emergency landing in the Thai capital Bangkok.' +
    'Smitivej Hospital, in Bangkok, said 104 people were treated and 58 remain in hospital, 20 of' +
    'whom are in the intensive care unit.' +
    'There are 15 Britons still being treated in hospital, with six in intensive care, the hospital said.';
    const matches = [...txt.matchAll(regex)];
    return matches.length;
}

const oddPositiveNumbers = async (numbers = [18, 0, -3, 66, 28, 9, -70, 120, 7], numberNine = 9) => {
    const arr = numbers.filter((number) => number > 0 && number % 2 !== 0);
    const result = arr.find((number) => number === numberNine);
    return result;
};

const getCustomer = async (customerId = 'X101') => {
    const BASE_URL = 'http://localhost/';
    const API_CUSTOMER_ENDPOINT = `${BASE_URL}v1/`;

    const getHeaders = (headersOption) => {
        const options = {
            'headers': {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=UTF-8',
                'responseType': 'application/json',
                ...headersOption
            }
        };
        return options;
    };

    const POST = async (url, payload = {}, headersOption, method = 'POST', timeout = 60000) => {
        try {
            const headers = getHeaders(headersOption);
            const data = JSON.stringify(payload);
            const options = { data, url, method, timeout, ...headers };
            // return await axios(options);
            return options;
        } catch (error) {
            console.error('Error during POST request:', error);
            throw error;
        }
    };

    const GetCustomerNameService = async (req = {}) => {
        return await POST(`${API_CUSTOMER_ENDPOINT}getCustomer`, req);
    };
    
    try {
        const response = await GetCustomerNameService({ customerId });
        return response.data;
    } catch (error) {
        console.error('Error:', error);
    }
};

const autoRun = () => {
    const func = [countOfWords(), oddPositiveNumbers(), getCustomer()];
    func.forEach(async (e, i) => console.log((i + 1) + '. Output = ' + await e));
}

autoRun();
