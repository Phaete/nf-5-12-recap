import {ToDo} from "../../types/ToDo.ts";

export type InProgressProps = {
	todos: ToDo[]
	fetchAll: () => void
}