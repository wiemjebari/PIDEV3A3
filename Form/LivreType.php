<?php

namespace App\Form;

use App\Entity\Livre;
use Doctrine\DBAL\Types\IntegerType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LivreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre', TextType::class,[

                'label'=>'Titre ' ,



                'attr'=>[
                    'placeholder'=>'Merci de définir le titre',
                    'class'=>'titre'

                ]
            ])//titre<input type=texte>

            ->add('editeur', TextType::class,[

                'label'=>'Editeur ',
                'attr'=>[
                    'placeholder'=>'Merci de définir l editeur',
                    'class'=>'editeur'

                ]
            ])//editeur<input type=texte>
            ->add('categorie', TextType::class,[

                'label'=>'Categorie ',
                'attr'=>[
                    'placeholder'=>'Merci de définir la categorie',
                    'class'=>'categorie'

                ]
            ])//categorie<input type=texte>
              ->add('duree', TextType::class,[

                'label'=>'Duree ',
                'attr'=>[
                    'placeholder'=>'Merci de définir la duree',
                    'class'=>'duree'

                ]
            ])//duree<input type=texte>
            ->add('source', TextType::class,[

                'label'=>'Source ',
                'attr'=>[
                    'placeholder'=>'Merci de définir la source',
                    'class'=>'source'

                ]
            ])//editeur<input type=texte>
            ->add('image', TextType::class,[

                'label'=>'Image',
                'attr'=>[
                    'placeholder'=>'Merci de définir l image',
                    'class'=>'image'

                ]
            ])//image<input type=texte>
            ->add('audio', TextType::class,[

                'label'=>'Audio',
                'attr'=>[
                    'placeholder'=>'Merci de définir l audio',
                    'class'=>'audio'

                ]
            ])//audio<input type=texte>



        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Livre::class,
        ]);
    }
}
