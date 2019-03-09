const DB = {
  people: [
    {
      id: 1,
      name: 'Jaeyeon Kim',
      age: 26,
      gender: 'male',
    },
    {
      id: 2,
      name: '2',
      age: 2,
      gender: 'male',
    },
    {
      id: 3,
      name: '3',
      age: 3,
      gender: 'female',
    },
  ],
  movies: [
    {
      id: 1,
      name: 'movie1',
      score: 1,
    },
    {
      id: 2,
      name: 'movie2',
      score: 2,
    },
    {
      id: 3,
      name: 'movie3',
      score: 3,
    },
  ]
}

export default DB

export const findAll = table => DB[table]

export const findById = (table, id) => DB[table].filter(e => e.id === id)[0]

export const deleteById = (table, id) => {
  const movie = findById(table, id)
  if (movie) {
    _delete(table, movie)
    return movie
  }

  return null
}

export const save = (table, data) => {
  data.id = _nextId(table)
  
  _save(table, data)
  return data
}

const _save = (table, data) => DB[table].push(data)
const _nextId = table => Math.max.apply(Math, DB[table].map(e => e.id)) + 1
const _delete = (table, target) => DB[table] = DB[table].filter(e => e !== target)