package models

type SearchForm struct {
	Difficulty string `json:"difficulty"`
	Keyword string `json:"keyword"`
	Length int	`json:"length"`
	Page int	`json:"page"`
}

type SearchCurriculumForm struct {
	Id int `json:"id"`
}
