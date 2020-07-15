package controllers

import (
	"articles/utils"
	"fmt"
	"github.com/astaxie/beego"
)

// TbUserController operations for TbUser
type TbUserController struct {
	beego.Controller
}

// URLMapping ...
func (c *TbUserController) URLMapping() {
	c.Mapping("Login",c.Login)
}
// Login ...
// @Title Login
// @Description create TbUser
// @Param	body		body 	models.TbUser	true		"body for TbUser content"
// @Success 201 {int} models.TbUser
// @Failure 403 body is empty
// @router /login [post]
func (c *TbUserController) Login(){
	username := c.GetString("username")
	password := c.GetString("password")
	fmt.Println(username,password)
	if token,err := utils.GenerateToken(username,password);err!=nil {
		c.Ctx.Output.SetStatus(201)
		c.Data["json"] = err.Error()
	} else {
		c.Data["json"] = token
	}
	c.ServeJSON()
}
