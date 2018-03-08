from typing import List

from sqlalchemy import Column, Integer, String, ForeignKey
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.ext.hybrid import hybrid_property
from sqlalchemy.orm import relationship

Base = declarative_base()


class User(Base):
    id = Column(Integer, primary_key=True)
    down_votes = Column(Integer)
    up_votes = Column(Integer)
    views_votes = Column(Integer)
    displayName = Column(String)


class Badge(Base):
    id = Column(Integer, primary_key=True)
    user_id = Column(Integer, ForeignKey(User.id))

    user = relationship(User, uselist=False, backref='comments')


class Post(Base):
    id = Column(Integer, primary_key=True)
    score = Column(Integer, default=0)
    body = Column(String)
    _tags = Column(String, name='tags')
    owner_user_id = Column(Integer, ForeignKey(User.id))
    accepted_answer_id = Column(Integer)

    TAGS_DELIMITER = ";"
    @hybrid_property
    def tags(self):
        return self._tags.split(self.TAGS_DELIMITER)

    @tags.setter
    def tags(self, new_tags: List[str]):
        self._tags = self.TAGS_DELIMITER.join(new_tags)

    user = relationship(User, uselist=False, backref='posts')
    post = relationship('Post', uselist=False)
    answer = relationship('Post', uselist=False)


class Comment(Base):
    id = Column(Integer, primary_key=True)
    score = Column(Integer, default=0)
    text = Column(String)
    post_id = Column(Integer, ForeignKey(Post.id))
    user_id = Column(Integer, ForeignKey(User.id))

    user = relationship(User, uselist=False, backref='comments')
    post = relationship(Post, uselist=False, backref='comments')
