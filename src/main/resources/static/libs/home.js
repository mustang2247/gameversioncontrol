/**
 * 新增应用版本信息
 * @param greetCardId
 * @param name
 */
function addCheckInfo() {
    $.get("/form/addCheckinfo");
}

/**
 * 新建配置文件
 * @param greetCardId
 * @param name
 */
function hotfixNewForm() {
    $.get("/form/hotfixNewForm");
}