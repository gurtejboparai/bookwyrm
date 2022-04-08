//This is a singleton to provide a single point of truth

const categoryList = [
    "Overall",
    "Adventure",
    "Action",
    "Bedtime",
    "Comedy/Humor",
    "Children's",
    "Drama",
    "Fantasy",
    "Gothic",
    "Horror",
    "Historical Fiction",
    "Alternate History",
    "Mystery",
    "Romance",
    "Sport",
    "Science-Fiction",
    "Thriller"
]

export default{
    getGenres(){
        return categoryList
    },

    //This returns the list of genres that actually are genres, ie "overall" is not included
    getStrictGenres(){
        return categoryList.slice(1, categoryList.length)
    }
}