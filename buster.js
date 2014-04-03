var config = exports;

config["Browser Tests"] = {
    environment: "browser",
    sources: [],
    tests: ["resources/js/my_cljs_project_browser_test.js",
            "resources/js/my_cljs_project_browser_optimized_test.js"]
};

config["Node Tests"] = {
    environment: "node",
    sources: [],
    tests: ["resources/js/my_cljs_project_node_test.js"]
};
