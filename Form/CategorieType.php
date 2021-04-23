<?php

namespace App\Form;

use App\Entity\Categorie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CategorieType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom', TextType::class,[

                'label'=>'Nom ' ,



                'attr'=>[
                    'placeholder'=>'Merci de définir le nom',
                    'class'=>'nom'

                ]
            ])//nom<input type=texte>
            ->add('description', TextType::class,[

                'label'=>'Description ',
                'attr'=>[
                    'placeholder'=>'Merci de définir la description',
                    'class'=>'description'

                ]
            ])//description<input type=texte>


        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Categorie::class,
        ]);
    }
}
