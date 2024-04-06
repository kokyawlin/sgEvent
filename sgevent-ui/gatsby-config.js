const { createProxyMiddleware } = require("http-proxy-middleware");
/**
 * @type {import('gatsby').GatsbyConfig}
 */
module.exports = {
  developMiddleware: (app) => {
    app.use(
      "/sgEvent",
      createProxyMiddleware({
        target: "http://localhost:8080/sgevent",
        pathRewrite: {
          "^/sgEvent": "",
        },
      })
    ),
      app.use(
        "/sgMap",
        createProxyMiddleware({
          target: "https://www.onemap.gov.sg/api/common/elastic",
          secure: false,
          changeOrigin: true,
          pathRewrite: {
            "^/sgMap": "",
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
