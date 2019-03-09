import DB, { findAll, findById, deleteById, save } from '../db'

export default {
  Query: {
    movies() {
      return findAll('movies')
    },
    movie(_, { id }) {
      return findById('movies', id)
    },
  },
  Mutation: {
    addMovie(_, { name, score }) {
      return save('movies', {
        name, 
        score,
      })
    },
    deleteMovie(_, { id }) {
      return deleteById('movies', id)
    }
  }
}
