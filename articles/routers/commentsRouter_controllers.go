package routers

import (
	"github.com/astaxie/beego"
	"github.com/astaxie/beego/context/param"
)

func init() {

    beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"] = append(beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"],
        beego.ControllerComments{
            Method: "SearchByPage",
            Router: "/searchByPage",
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"] = append(beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"],
        beego.ControllerComments{
            Method: "SearchHot",
            Router: "/searchHot",
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"] = append(beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"],
        beego.ControllerComments{
            Method: "SearchRefined",
            Router: "/searchRefined",
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"] = append(beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"],
        beego.ControllerComments{
            Method: "SearchTop",
            Router: "/searchTop",
            AllowHTTPMethods: []string{"get"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"] = append(beego.GlobalControllerRouter["articles/controllers:TbCurriculumController"],
        beego.ControllerComments{
            Method: "SearchCurriculum",
            Router: "/searchCurriculum",
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

    beego.GlobalControllerRouter["articles/controllers:TbUserController"] = append(beego.GlobalControllerRouter["articles/controllers:TbUserController"],
        beego.ControllerComments{
            Method: "Login",
            Router: "/login",
            AllowHTTPMethods: []string{"post"},
            MethodParams: param.Make(),
            Filters: nil,
            Params: nil})

}
