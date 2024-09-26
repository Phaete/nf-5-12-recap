import {ToDo} from "../../types/ToDo.ts";

export type TodoCardProps = {
	todo: ToDo
	fetchAll: () => void
}