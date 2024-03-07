const { createProxyMiddleware } = require("http-proxy-middleware");
/**
 * @type {import('gatsby').GatsbyConfig}
 */
module.exports = {
  developMiddleware: (app) => {
    app.use(
      "/sgEvent/",
      createProxyMiddleware({
        target: "http://localhost:8080",
        pathRewrite: {
          "/sgEvent/": "",
        },
      })
    );
  },
  siteMetadata: {
    title: `community-event`,
    siteUrl: `https://www.yourdomain.tld`,
  },
  plugins: ["gatsby-plugin-sitemap"],
};
