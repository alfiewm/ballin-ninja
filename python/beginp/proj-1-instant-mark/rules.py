class Rule:
    '''
    Base calss for all rules.
    '''
    def action(self, block, handler):
        handler.start(self.type)
        handler.feed(block)
        handler.end(self.type)
        return True

class HeadLineRule(Rule):
    '''
    A heading is a single line that is at most 70 chars
    '''
    type = 'heading'
    def condition(self, block):
        return not '\n' in block and len(block) <= 70 and not block[-1] == ':'

class TitleRule(HeadLineRule):
    '''
    The title is the first block in the doc, provided that it is a heading
    '''
    type = 'title'
    first = True
    def condition(self, block):
        if not self.first: return False
        self.first = False
        return HeadLineRule.condition(self, block)

class ListItemRule(Rule):
    '''
    A list item is a paragraph that begins with a pyphen.
    '''
    type = 'listitem'
    def condition(self, block):
        return block[0] == '-'
    def action(self, block, handler):
        handler.start(self.type)
        handler.feed(block[1:].strip())
        handler.end(self.type)
        return True

class ListRule(ListItemRule):
    '''
    A list begins between a block that is not a list item and a subsequent
    list item. It ends after the last convsecutive list item.
    '''
    type = 'list'
    inside = False
    def condition(self, block):
        return True
    def action(self, block, handler):
        if not self.inside and ListItemRule.condition(self, block):
            handler.start(self.type)
            self.inside = True
        elif self.inside and not ListItemRule.condition(self, block):
            handler.end(self.type)
            self.inside = False
        return False

class ParagraphRule(Rule):
    '''
    A parapraph is simple a block that isn't covered by any of the other rules.
    '''
    type = 'paragraph'
    def condition(self, block):
        return True 
