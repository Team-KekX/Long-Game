const config = {
  API_BASE_URL: process.env.NODE_ENV === 'production'
    ? 'https://api.yourproductionurl.com'
    : 'http://localhost:3000',
};

export default config;
